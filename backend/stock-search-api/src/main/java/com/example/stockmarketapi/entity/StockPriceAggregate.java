package com.example.stockmarketapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "stock_price_aggregation")
@Data
public class StockPriceAggregate {
    @Id
    private String symbol;
    private double price;
    private long volume;
    private Instant timestamp;
}
