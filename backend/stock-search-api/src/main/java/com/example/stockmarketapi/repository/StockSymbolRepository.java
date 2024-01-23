package com.example.stockmarketapi.repository;

import com.example.stockmarketapi.entity.StockSymbol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockSymbolRepository extends MongoRepository<StockSymbol, String> {
}
