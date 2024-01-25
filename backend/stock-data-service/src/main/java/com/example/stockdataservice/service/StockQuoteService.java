package com.example.stockdataservice.service;

import com.example.stockdataservice.config.ApiProperties;
import com.example.stockdataservice.model.StockQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class StockQuoteService {
    @Autowired
    private WebClient webClient;

    @Autowired
    private ApiProperties apiProperties;

    public Mono<StockQuote> fetchStockQuote(String symbol) {
        return webClient.get()
                .uri("/quote/" + symbol + "/?apikey=" + apiProperties.getKey())
                .retrieve()
                .bodyToMono(StockQuote[].class).map(response -> response.length > 0 ? response[0] : null)
                .onErrorResume(WebClientResponseException.class, e -> {
                    // Log the error or take additional actions
                    System.out.println("Error fetching stock data: " + e.getMessage());
                    return Mono.empty(); // Return an empty Mono to indicate an error
                });
    }
}
