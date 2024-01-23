package com.example.stockmarketapi.repository;

import com.example.stockmarketapi.entity.StockPrice;
import com.example.stockmarketapi.entity.StockPriceAggregate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceAggregateRepository extends MongoRepository<StockPriceAggregate, String> {
}
