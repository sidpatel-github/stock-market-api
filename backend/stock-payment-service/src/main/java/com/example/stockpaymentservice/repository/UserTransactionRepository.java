package com.example.stockpaymentservice.repository;

import com.example.stockpaymentservice.entity.UserTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends MongoRepository<UserTransaction, String> {
}
