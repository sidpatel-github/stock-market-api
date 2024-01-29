package com.example.stockwishlistservice.repository;

import com.example.stockwishlistservice.entity.StockWishList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishlistRepository extends MongoRepository<StockWishList, String> {
}
