package com.example.stocksconsumer.logic;

import com.example.stocksconsumer.dao.CompanyDTO;
import com.example.stocksconsumer.dao.CompanyRepository;
import com.example.stocksconsumer.dao.StockDTO;
import com.example.stocksconsumer.dao.StockRepository;
import com.example.stocksconsumer.mapper.Mapper;
import com.example.stocksconsumer.models.Companies;
import com.example.stocksconsumer.models.Stock;
import com.example.stocksconsumer.ws.ApperateClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.example.stocksconsumer.mapper.Mapper.mapToStockDTO;

@Component
public class Processor {

    protected final Logger logger = LoggerFactory.getLogger(Processor.class);


    private final ApperateClient client;
    private final StockRepository stockRepository;
    private final CompanyRepository companyRepository;
    private final ExecutorService stocksSaverExecutor;

    // очередь между потоком отправки запросов на стоимость акций и потоком на сохранение
    BlockingQueue<StockDTO> stockRequestSaveQueue = new LinkedBlockingQueue<>();

    // пул нужен для того, чтобы соблюсти batch=200 на сохранение акций
    BlockingQueue<StockDTO> stockSavePool = new LinkedBlockingQueue<>();


    // список компаний (in memory), что-то вроде кеша который мы обновляем раз в час (не так часто как стоимость акций)
    BlockingQueue<CompanyDTO> companiesForSave = new LinkedBlockingQueue<>();


    public Processor(ApperateClient client, CompanyRepository companyRepository, StockRepository stockRepository) {
        this.client = client;
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
        this.stocksSaverExecutor = Executors.newSingleThreadExecutor();
    }

    public void processCompanies() {
        long started = System.nanoTime();

        Companies companies = client.sendCompanyRequest(); // отправляем запрос на компании и получаем список в ответе
        // мапим в dto
        List<CompanyDTO> dtos = companies.stream()
                .map(Mapper::mapToCompanyDTO)
                .collect(Collectors.toList());

        CompletableFuture.supplyAsync(() -> companyRepository.saveAll(dtos)); //отправляем на сохранение в отдельном потоке

        //очищаем старые значения в пуле
        companiesForSave.clear();
        logger.info("companiesForSave cleared");


        // заполняем очередь компаний активными компаниями для последующей вычитки и отправки запросов на акции
//        dtos.stream().filter(CompanyDTO::isEnabled).forEach(it -> {
        dtos.forEach(it -> {
            try {
                companiesForSave.put(it);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        });

        long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - started);
        logger.info("processCompanies() {} msecs", elapsed);
        logger.info("companies size after filling {}", companies.size());
    }




    public void processStocks() {
        long started = System.nanoTime();
        logger.info("processStocks() started with companiesForSave.size(): {}", companiesForSave.size());

        // на каждую компанию из списка запросить информацию по акциям и положить все в очередь на сохранение (многопоточно)
        companiesForSave.forEach(it -> {
            CompletableFuture.supplyAsync(() -> it)
                        .thenApply(company -> client.stockRequest(company.getSymbol())) // отправить запросы
                        .thenAccept(this::putStocksToQueue)  // ответы положить в очередь на маппинг и сохранение
                        .exceptionally(ex -> {
                            logger.error("Error in CompletableFuture: " + ex);
                            return null;
                        } );
        });

        // запустить отдельный поток, который будет вытаскивать из очереди и сохранять в базу пачками по 200
        stocksSaverExecutor.submit(this::pollAndSaveStocksFromQueue);

        long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - started);
        logger.info("processStocks() {} msecs", elapsed);
    }


    private void putStocksToQueue(Stock stock) {
        logger.info("putStocksToQueue() started");
        StockDTO stockDTO = mapToStockDTO(stock);
        try {
                stockRequestSaveQueue.put(stockDTO);
                logger.info("putStocksToQueue() done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                throw new IllegalStateException(e);
            }
    }

    private void pollAndSaveStocksFromQueue() {
        boolean interrupted = false;
        while (!interrupted) {
            try {
                logger.info("...wait for take");
                StockDTO data = stockRequestSaveQueue.poll(2, TimeUnit.SECONDS); // если на момент запроса (окончание таймаута) нет данных вернет null
                if (data != null) {
                    stockSavePool.add(data);
                    logger.info("data saved to list");
                    if (stockSavePool.size() >= 200) {
                        stockRepository.saveAll(stockSavePool);
                        logger.info("saved butch with 200 stocks" );
                        stockSavePool.clear();
                    }
                } else {
                    logger.info("No data available");
                    stockRepository.saveAll(stockSavePool);
                    logger.info("saved butch with stocks size: " + stockSavePool.size() );
                    stockSavePool.clear();
                    logger.info("Exit from saverThread");
                    break;
                }

                // Проверяем флаг прерывания и выходим из цикла, если поток был прерван
                interrupted = Thread.interrupted();
            } catch (InterruptedException e) {
                logger.error("Error while waiting for data", e);
                interrupted = true; // Устанавливаем флаг прерывания
            }

        }
    }



    public void reportPayLoad() {

        List<StockDTO> fastestGrowsStocks = stockRepository.findTopFiveFastestGrowingStocks();
        System.out.println("fastestGrowsStocks: " + fastestGrowsStocks);

        List<StockDTO> expensiveStocks = stockRepository.findTopFiveExpensiveStocks();
        System.out.println("expensiveStocks: " + expensiveStocks);

    }


}
