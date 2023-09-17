package com.example.stocksconsumer.mapper;

import com.example.stocksconsumer.dao.CompanyDTO;
import com.example.stocksconsumer.dao.StockDTO;
import com.example.stocksconsumer.models.Company;
import com.example.stocksconsumer.models.Stock;

public class Mapper {

    public static CompanyDTO mapToCompanyDTO(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(company.getName());
        companyDTO.setEnabled(company.isEnabled());
        companyDTO.setSymbol(company.getSymbol());
        return companyDTO;
    }

    public static StockDTO mapToStockDTO(Stock stock) {
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

}
