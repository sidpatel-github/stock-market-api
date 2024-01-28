package com.example.stockmarketapi.error.exception;

public class StockQuoteNotFoundException extends RuntimeException {
    public StockQuoteNotFoundException() {
        super();
    }

    public StockQuoteNotFoundException(String message) {
        super(message);
    }
}
