package com.example.stockmarketapi.controller;

import com.example.stockmarketapi.entity.StockSymbol;
import com.example.stockmarketapi.repository.StockSymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/stock-search")
public class StockSearchController {

    @Autowired
    StockSymbolRepository stockSymbolRepository;

    @GetMapping("/symbols")
    public List<StockSymbol> getAllStockSymbols(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<StockSymbol> pageResult = stockSymbolRepository.findAll(PageRequest.of(page, size));
        return pageResult.getContent();
    }

    @GetMapping("/symbols/{symbolID}")
    public List<StockSymbol> getMatchingStockSymbols(@PathVariable String symbolID) {
        return stockSymbolRepository.findBySymbolStartingWith(symbolID);
    }

}
