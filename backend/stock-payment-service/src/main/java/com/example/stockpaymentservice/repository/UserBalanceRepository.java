package com.example.stockpaymentservice.repository;

import com.example.stockpaymentservice.entity.UserBalance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends MongoRepository<UserBalance, String> {
}
