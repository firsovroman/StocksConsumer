package com.example.stocksfetcher.mapper;

import com.example.stocksfetcher.dao.CompanyDTO;
import com.example.stocksfetcher.dao.StockDTO;
import com.example.stocksfetcher.models.Company;
import com.example.stocksfetcher.models.Stock;

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

        stockDTO.setChange(stock.getChange());
        stockDTO.setCompanyName(stock.getCompanyName());
        stockDTO.setLatestPrice(stock.getLatestPrice());
        stockDTO.setPreviousVolume(stock.getPreviousVolume());
        stockDTO.setSymbol(stock.getSymbol());
        stockDTO.setVolume(stock.getVolume());

        return stockDTO;
    }

}
