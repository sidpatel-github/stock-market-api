package com.example.stockorderservice.repository;

import com.example.stockorderservice.entity.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<PurchaseOrder, String> {
    List<PurchaseOrder> findByUserId(String userId);
}
