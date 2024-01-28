package com.example.stockdataservice.repository;

import com.example.stockdataservice.entity.StockPrice;
import com.example.stockdataservice.entity.StockPriceAggregate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Meta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface StockPriceRepository extends ReactiveMongoRepository<StockPrice, String> {
    @Aggregation(pipeline = {
            "{ $group: { _id: '$symbol', latestDocument: { $first: '$$ROOT' } } }",
            "{ $sort: { symbol: 1, timestamp: -1 } }",
            "{ $replaceRoot: { newRoot: { $mergeObjects: ['$latestDocument', { _id: '$_id' }] } } }",
            "{ $project: { symbol: 0 } }",
            "{ $out: 'stock_price_aggregation'}"
    })
    Flux<StockPriceAggregate> performAggregationOnStockPrices();
}
