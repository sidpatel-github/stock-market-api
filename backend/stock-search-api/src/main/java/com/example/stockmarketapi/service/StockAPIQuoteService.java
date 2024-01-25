package com.example.stockmarketapi.service;

import com.example.stockmarketapi.model.StockQuote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "STOCK-DATA-SERVICE")
public interface StockAPIQuoteService {

    @GetMapping("/stock-api/quote/{symbol}")
    StockQuote getStockQuote(@PathVariable("symbol") String symbol);
}
