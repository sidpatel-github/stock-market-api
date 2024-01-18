package com.example.stockdataservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "stock_symbol")
@Data
public class StockSymbol {
    @Id
    private String symbol;
    private String name;
    private String exchange;
}
