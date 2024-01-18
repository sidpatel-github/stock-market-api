package com.example.stockdataservice.service;

import com.example.stockdataservice.config.ApiProperties;
import com.example.stockdataservice.entity.StockSymbol;
import com.example.stockdataservice.repository.StockSymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StockSymbolService {
    @Autowired
    private WebClient webClient;

    @Autowired
    private StockSymbolRepository stockSymbolRepository;

    @Autowired
    private ApiProperties apiProperties;

    public Mono<Void> fetchAndStoreStockSymbol(String exchange) {
        return stockSymbolRepository.deleteAll().thenMany(
                webClient.get()
                        .uri("/symbol/" + exchange + "/?apikey=" + apiProperties.getKey())
                        .retrieve()
                        .bodyToFlux(StockSymbol.class)
                ).buffer(500)
                .flatMap(batch -> stockSymbolRepository.saveAll(batch))
                .then();
    }
}
