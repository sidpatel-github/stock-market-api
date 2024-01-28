package com.example.stockmarketapi.error.decoder;

import com.example.stockmarketapi.error.exception.StockQuoteNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String message, Response response) {
        if (response.status() == 404) {
            return new StockQuoteNotFoundException(message);
        }
        return new Exception("Something went wrong from server");    }
}
