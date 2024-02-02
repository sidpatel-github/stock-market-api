package com.example.stockorderservice.repository;

import com.example.stockorderservice.entity.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<PurchaseOrder, String> {
}
