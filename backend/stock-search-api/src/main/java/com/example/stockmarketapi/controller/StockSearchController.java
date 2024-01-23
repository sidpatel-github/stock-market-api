package com.example.stockmarketapi.controller;

import com.example.stockmarketapi.entity.StockSymbol;
import com.example.stockmarketapi.repository.StockSymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/stock-search")
public class StockSearchController {

    @Autowired
    StockSymbolRepository stockSymbolRepository;

    @GetMapping("/symbols")
    public List<StockSymbol> getAllStockSymbols() {
        return stockSymbolRepository.findAll();
    }

}
