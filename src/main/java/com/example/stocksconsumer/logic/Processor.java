package com.example.stocksconsumer.logic;

import com.example.stocksconsumer.dao.CompanyDTO;
import com.example.stocksconsumer.dao.CompanyRepository;
import com.example.stocksconsumer.dao.StockDTO;
import com.example.stocksconsumer.dao.StockRepository;
import com.example.stocksconsumer.entity.Companies;
import com.example.stocksconsumer.entity.Company;
import com.example.stocksconsumer.entity.Stock;
import com.example.stocksconsumer.ws.ApperateClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Processor {

    private final ApperateClient client;
    private final StockRepository stockRepository;
    private final CompanyRepository companyRepository;


    public Processor(ApperateClient client, CompanyRepository companyRepository, StockRepository stockRepository) {
        this.client = client;
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
    }


    public void execute() {
        List<StockDTO> dtoStocks = new ArrayList<>();
        Companies companies = client.companyRequest();
        List<CompanyDTO> dtos = companies.stream().map(this::mapToCompanyDTO).collect(Collectors.toList());
        companyRepository.saveAll(dtos);

        for (Company c : companies) {
            Stock stock = client.stockRequest(c.getSymbol());
            dtoStocks.add(mapToStockDTO(stock));
        }
        stockRepository.saveAll(dtoStocks);
    }


    public CompanyDTO mapToCompanyDTO(Company company) {
            CompanyDTO companyDTO = new CompanyDTO();

            companyDTO.setAddress(company.getAddress());
            companyDTO.setAddress2(company.getAddress2());
            companyDTO.setCeo(company.getCeo());
            companyDTO.setCity(company.getCity());
            companyDTO.setCompanyName(company.getCompanyName());
            companyDTO.setCountry(company.getCountry());
            companyDTO.setDate(company.getDate());
            companyDTO.setEmployees(company.getEmployees());
            companyDTO.setExchange(company.getExchange());
            companyDTO.setExchangeCode(company.getExchangeCode());
            companyDTO.setIndustry(company.getIndustry());
            companyDTO.setIssuetype(company.getIssuetype());
            companyDTO.setLongDescription(company.getLongDescription());
            companyDTO.setMarketcap(company.getMarketcap());
            companyDTO.setPhone(company.getPhone());
            companyDTO.setPrimarySicCode(company.getPrimarySicCode());
            companyDTO.setSector(company.getSector());
            companyDTO.setSecurityName(company.getSecurityName());
            companyDTO.setSecurityType(company.getSecurityType());
            companyDTO.setShortDescription(company.getShortDescription());
            companyDTO.setState(company.getState());
            companyDTO.setSymbol(company.getSymbol());
            companyDTO.setWebsite(company.getWebsite());
            companyDTO.setZip(company.getZip());
            companyDTO.setId(company.getId());
            companyDTO.setKey(company.getKey());
            companyDTO.setSubkey(company.getSubkey());
            companyDTO.setUpdated(company.getUpdated());

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
