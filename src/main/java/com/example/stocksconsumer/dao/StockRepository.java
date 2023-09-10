package com.example.stocksconsumer.dao;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<StockDTO, Long> {

}
