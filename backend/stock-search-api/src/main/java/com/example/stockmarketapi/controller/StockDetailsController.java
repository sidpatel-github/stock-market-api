package com.example.stockmarketapi.controller;

import com.example.stockmarketapi.entity.StockPriceAggregate;
import com.example.stockmarketapi.repository.StockPriceAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@RequestMapping("/stock-detail")
public class StockDetailsController {

    @Autowired
    StockPriceAggregateRepository stockPriceAggregateRepository;

    @GetMapping("/{symbolID}")
    public Optional<StockPriceAggregate> getStockDetails(@PathVariable String symbolID) {
        return stockPriceAggregateRepository.findById(symbolID);
    }
}
