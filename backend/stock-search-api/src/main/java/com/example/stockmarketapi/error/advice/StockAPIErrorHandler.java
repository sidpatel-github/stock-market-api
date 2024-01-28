package com.example.stockmarketapi.error.advice;

import com.example.stockmarketapi.error.exception.StockQuoteNotFoundException;
import com.example.stockmarketapi.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class StockAPIErrorHandler {

    @ExceptionHandler(StockQuoteNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(StockQuoteNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
