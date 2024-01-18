package com.example.stockdataservice.controller;

import com.example.stockdataservice.service.StockSymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("/stock-data-service")
public class StockDataController {

    @Autowired
    StockSymbolService stockSymbolService;

    @GetMapping("/{exchange}")
    public Mono<ResponseEntity<String>> populateDBwithSymbols(@PathVariable String exchange) {
        return stockSymbolService.fetchAndStoreStockSymbol(exchange)
                .then(Mono.just(ResponseEntity.ok("Success")))
                .onErrorResume(AsyncRequestTimeoutException.class, ex ->
                        Mono.just(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timed out")))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage())));
    }
}
