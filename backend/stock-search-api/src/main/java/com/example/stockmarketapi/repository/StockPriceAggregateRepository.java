package com.example.stockmarketapi.repository;

import com.example.stockmarketapi.entity.StockPriceAggregate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceAggregateRepository extends MongoRepository<StockPriceAggregate, String> {
}
