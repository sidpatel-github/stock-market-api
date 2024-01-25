package com.example.stockdataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockQuote {
    public String symbol;
    public String name;
    public double price;
    public double changesPercentage;
    public double change;
    public double dayLow;
    public double dayHigh;
    public double yearHigh;
    public double yearLow;
    public long marketCap;
    public double priceAvg50;
    public double priceAvg200;
    public String exchange;
    public int volume;
    public int avgVolume;
    @JsonProperty("open")
    public double myopen;
    public double previousClose;
    public double eps;
    public double pe;
    public long sharesOutstanding;
}
