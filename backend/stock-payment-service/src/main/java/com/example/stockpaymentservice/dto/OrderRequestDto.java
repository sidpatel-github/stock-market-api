package com.example.stockpaymentservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequestDto {
    private String id;
    private String userId;
    private String stockSymbol;
    private Integer amount;
    private Integer quantity;
}
