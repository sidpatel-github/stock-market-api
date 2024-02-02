package com.example.stockorderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private String id;
    private String stockSymbol;
    private Integer amount;
    private Integer quantity;
}
