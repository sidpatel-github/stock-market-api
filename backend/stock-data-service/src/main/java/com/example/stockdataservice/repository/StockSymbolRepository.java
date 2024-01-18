package com.example.stockdataservice.repository;

import com.example.stockdataservice.entity.StockSymbol;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface  StockSymbolRepository extends ReactiveMongoRepository<StockSymbol, String> {
}
