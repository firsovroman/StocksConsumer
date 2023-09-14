package com.example.stocksconsumer.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class StockRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    StockRepository stockRepository;


    @Test
    void test_company() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName("Apple");
        companyDTO.setSymbol("aapl");
        companyDTO.setEnabled(true);
        CompanyDTO saved = companyRepository.save(companyDTO);
        System.out.println(saved.getCompanyId());
    }


    @Test
    void test() {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setCalculationPrice("122323");
        stockDTO.setCompanyName("hong2323");
        StockDTO saved = stockRepository.save(stockDTO);
        System.out.println(saved.getStockId());
    }

    @Test
    void test2() {

        Optional<StockDTO> stockDTO = stockRepository.findById(3L);
        System.out.println(stockDTO.get());
    }

}