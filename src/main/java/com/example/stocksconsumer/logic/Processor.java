package com.example.stocksconsumer.logic;

import com.example.stocksconsumer.dao.CompanyDTO;
import com.example.stocksconsumer.dao.CompanyRepository;
import com.example.stocksconsumer.dao.StockDTO;
import com.example.stocksconsumer.dao.StockRepository;
import com.example.stocksconsumer.models.Companies;
import com.example.stocksconsumer.models.Company;
import com.example.stocksconsumer.models.Stock;
import com.example.stocksconsumer.ws.ApperateClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
                .map(this::mapToCompanyDTO)
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



    public CompanyDTO mapToCompanyDTO(Company company) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setName(company.getName());
            companyDTO.setEnabled(company.isEnabled());
            companyDTO.setSymbol(company.getSymbol());
            return companyDTO;
    }

    public StockDTO mapToStockDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();

        stockDTO.setAvgTotalVolume(stock.getAvgTotalVolume());
        stockDTO.setCalculationPrice(stock.getCalculationPrice());
        stockDTO.setChange(stock.getChange());
        stockDTO.setChangePercent(stock.getChangePercent());
        stockDTO.setClose(stock.getClose());
        stockDTO.setCloseSource(stock.getCloseSource());
        stockDTO.setCloseTime(stock.getCloseTime());
        stockDTO.setCompanyName(stock.getCompanyName());
        stockDTO.setCurrency(stock.getCurrency());
        stockDTO.setDelayedPrice(stock.getDelayedPrice());
        stockDTO.setDelayedPriceTime(stock.getDelayedPriceTime());
        stockDTO.setExtendedChange(stock.getExtendedChange());
        stockDTO.setExtendedChangePercent(stock.getExtendedChangePercent());
        stockDTO.setExtendedPrice(stock.getExtendedPrice());
        stockDTO.setExtendedPriceTime(stock.getExtendedPriceTime());
        stockDTO.setHigh(stock.getHigh());
        stockDTO.setHighSource(stock.getHighSource());
        stockDTO.setHighTime(stock.getHighTime());
        stockDTO.setIexAskPrice(stock.getIexAskPrice());
        stockDTO.setIexAskSize(stock.getIexAskSize());
        stockDTO.setIexBidPrice(stock.getIexBidPrice());
        stockDTO.setIexBidSize(stock.getIexBidSize());
        stockDTO.setIexClose(stock.getIexClose());
        stockDTO.setIexCloseTime(stock.getIexCloseTime());
        stockDTO.setIexLastUpdated(stock.getIexLastUpdated());
        stockDTO.setIexMarketPercent(stock.getIexMarketPercent());
        stockDTO.setIexOpen(stock.getIexOpen());
        stockDTO.setIexOpenTime(stock.getIexOpenTime());
        stockDTO.setIexRealtimePrice(stock.getIexRealtimePrice());
        stockDTO.setIexRealtimeSize(stock.getIexRealtimeSize());
        stockDTO.setIexVolume(stock.getIexVolume());
        stockDTO.setLastTradeTime(stock.getLastTradeTime());
        stockDTO.setLatestPrice(stock.getLatestPrice());
        stockDTO.setLatestSource(stock.getLatestSource());
        stockDTO.setLatestTime(stock.getLatestTime());
        stockDTO.setLatestUpdate(stock.getLatestUpdate());
        stockDTO.setLatestVolume(stock.getLatestVolume());
        stockDTO.setLow(stock.getLow());
        stockDTO.setLowSource(stock.getLowSource());
        stockDTO.setLowTime(stock.getLowTime());
        stockDTO.setMarketCap(stock.getMarketCap());
        stockDTO.setOddLotDelayedPrice(stock.getOddLotDelayedPrice());
        stockDTO.setOddLotDelayedPriceTime(stock.getOddLotDelayedPriceTime());
        stockDTO.setOpen(stock.getOpen());
        stockDTO.setOpenTime(stock.getOpenTime());
        stockDTO.setOpenSource(stock.getOpenSource());
        stockDTO.setPeRatio(stock.getPeRatio());
        stockDTO.setPreviousClose(stock.getPreviousClose());
        stockDTO.setPreviousVolume(stock.getPreviousVolume());
        stockDTO.setPrimaryExchange(stock.getPrimaryExchange());
        stockDTO.setSymbol(stock.getSymbol());
        stockDTO.setVolume(stock.getVolume());
        stockDTO.setWeek52High(stock.getWeek52High());
        stockDTO.setWeek52Low(stock.getWeek52Low());
        stockDTO.setYtdChange(stock.getYtdChange());
        stockDTO.setUSMarketOpen(stock.isUSMarketOpen());

        return stockDTO;
    }




    public void report() {

        Comparator<StockDTO> stockDTOComparator
                = Comparator.comparing(
                StockDTO::getVolume, Comparator.reverseOrder())
                .thenComparing(StockDTO::getPreviousVolume, Comparator.reverseOrder())
                .thenComparing(StockDTO::getCompanyName, Comparator.naturalOrder());

        List<StockDTO> stocks = (List<StockDTO>) stockRepository.findAll();
        stocks.stream().sorted(stockDTOComparator).limit(5).forEach(System.out::println);

    }


}
