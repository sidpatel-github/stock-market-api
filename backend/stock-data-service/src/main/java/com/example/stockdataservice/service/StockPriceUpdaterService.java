package com.example.stockdataservice.service;

import com.example.stockdataservice.config.ApiProperties;
import com.example.stockdataservice.entity.StockPrice;
import com.example.stockdataservice.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

@Service
public class StockPriceUpdaterService {
    @Autowired
    private WebClient webClient;

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Autowired
    private ApiProperties apiProperties;

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
                        () -> System.out.println("Completed updating stock prices at: " + Instant.now())
                );
    }
}
