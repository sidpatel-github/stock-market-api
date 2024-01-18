package com.example.stockdataservice.service;

import com.example.stockdataservice.config.ApiProperties;
import com.example.stockdataservice.entity.StockPrice;
import com.example.stockdataservice.entity.StockPriceAggregate;
import com.example.stockdataservice.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

@Service
public class StockPriceUpdaterService {
    @Autowired
    private WebClient webClient;

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Autowired
    private ApiProperties apiProperties;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Scheduled(fixedRate = 600000)
    public void fetchAndStoreStockPrices() {
        Instant timestamp = Instant.now();

        System.out.println("Fetching stock prices at : " + timestamp);
        webClient.get()
                .uri("/stock/full/real-time-price/?apikey=" + apiProperties.getKey())
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .map(stockPrice -> {
                    stockPrice.setTimestamp(timestamp);
                    return stockPrice;
                }).buffer(500)
                .flatMap(stockPriceRepository::saveAll)
                .subscribe(null, // onNext handler, you can keep it null if you don't need to process each emitted item
                        error -> System.err.println("Error occurred: " + error),
                        () -> {
                            System.out.println("Completed updating stock prices at: " + Instant.now());
                            System.out.println("Doing Aggregation task...." + Instant.now());
                            Mono<List<StockPriceAggregate>> latestPrices = stockPriceRepository.performAggregationOnStockPrices().collectList();
                            latestPrices.subscribe(
                                    aggregatedList -> {
                                        // Process the list here
                                        System.out.println("Aggregation completed. Number of latest prices: " + aggregatedList.size());
                                        System.out.println("Aggregation task DONE!!" + Instant.now());
                                    },
                                    error -> {
                                        // Handle error
                                        System.err.println("Error: " + error.getMessage());
                                    }
                            );
                        }
                );
    }

    public List<StockPriceAggregate> aggregateStockPriceData() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sort(Sort.by(Sort.Direction.ASC, "symbol").and(Sort.by(Sort.Direction.DESC, "timestamp"))),
                Aggregation.group("symbol").first("$$ROOT").as("latestDocument")
        );

        AggregationResults<StockPriceAggregate> results = mongoTemplate.aggregate(aggregation, "stock_price", StockPriceAggregate.class);
        return results.getMappedResults();
    }

}
