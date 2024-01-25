package com.example.stockdataservice.controller;

import com.example.stockdataservice.model.StockQuote;
import com.example.stockdataservice.service.StockQuoteService;
import com.example.stockdataservice.service.StockSymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController()
@RequestMapping("/stock-api")
public class StockDataController {

    @Autowired
    StockSymbolService stockSymbolService;

    @Autowired
    StockQuoteService stockQuoteService;

    @PostMapping("/exchange")
    public Mono<ResponseEntity<String>> populateDBwithSymbols(@RequestBody Map<String, String> request) {
        String exchange = request.get("exchange");
        return stockSymbolService.fetchAndStoreStockSymbol(exchange)
                .then(Mono.just(ResponseEntity.ok("Success")))
                .onErrorResume(AsyncRequestTimeoutException.class, ex ->
                        Mono.just(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timed out")))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage())));
    }

    @GetMapping("/quote/{symbol}")
    public Mono<ResponseEntity<StockQuote>> fetchStockQuote(@PathVariable String symbol) {
        return stockQuoteService.fetchStockQuote(symbol)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
