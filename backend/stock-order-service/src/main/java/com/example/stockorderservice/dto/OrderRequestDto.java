package com.example.stockorderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private String id;
    @JsonIgnore
    private String userId;
    private String stockSymbol;
    private Integer amount;
    private Integer quantity;
}
