package com.example.stocksconsumer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<StockDTO, Long> {

    @Query(value = "SELECT * FROM stocks s ORDER BY CASE WHEN s.volume IS NOT null AND s.volume <> 0 THEN s.volume ELSE s.previous_volume end DESC, s.company_name ASC LIMIT 5", nativeQuery = true)
    List<StockDTO> findTopFiveExpensiveStocks();


    @Query(value = "SELECT * FROM stocks s ORDER BY s.latest_price DESC LIMIT 5", nativeQuery = true)
    List<StockDTO> findTopFiveFastestGrowingStocks();

}
