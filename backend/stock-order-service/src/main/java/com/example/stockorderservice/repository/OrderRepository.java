package com.example.stockorderservice.repository;

import com.example.stockorderservice.entity.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<PurchaseOrder, String> {
    List<PurchaseOrder> findByUserId(String userId);
}
