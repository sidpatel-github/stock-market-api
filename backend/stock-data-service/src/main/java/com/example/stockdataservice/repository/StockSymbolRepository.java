package com.example.stockdataservice.repository;

import com.example.stockdataservice.entity.StockSymbol;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StockSymbolRepository extends ReactiveMongoRepository<StockSymbol, String> {
}
