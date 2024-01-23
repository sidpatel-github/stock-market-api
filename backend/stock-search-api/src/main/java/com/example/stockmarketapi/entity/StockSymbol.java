package com.example.stockmarketapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stock_symbol")
@Data
public class StockSymbol {
    @Id
    private String symbol;
    private String name;
    private String exchange;
}
