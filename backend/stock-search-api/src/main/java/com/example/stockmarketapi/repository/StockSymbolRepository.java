package com.example.stockmarketapi.repository;

import com.example.stockmarketapi.entity.StockSymbol;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockSymbolRepository extends PagingAndSortingRepository<StockSymbol, String> {

    @Query("{ 'symbol': { $regex: '^?0', $options: 'i' } }")
    List<StockSymbol> findBySymbolStartingWith(String regex);
}
