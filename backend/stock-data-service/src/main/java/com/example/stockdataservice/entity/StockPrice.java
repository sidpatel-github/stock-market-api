package com.example.stockdataservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TimeSeries;

import java.time.Instant;

@TimeSeries(collection="stock_price", timeField = "timestamp", metaField = "symbol")
@Data
public class StockPrice {
    private String symbol;

    @Field(name = "price")
    private double askPrice;
    private long volume;
    Instant timestamp;
}
