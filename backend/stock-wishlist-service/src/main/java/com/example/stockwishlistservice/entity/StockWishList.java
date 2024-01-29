package com.example.stockwishlistservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "wishlist")
public class StockWishList {
    @Id
    private String email;
    private Set<String> stocks = new LinkedHashSet<>();
}
