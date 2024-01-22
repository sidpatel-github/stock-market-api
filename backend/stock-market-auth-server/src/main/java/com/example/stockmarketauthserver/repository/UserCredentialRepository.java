package com.example.stockmarketauthserver.repository;

import com.example.stockmarketauthserver.entity.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends MongoRepository<UserCredential, String> {
}
