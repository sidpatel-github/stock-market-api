package com.example.stockmarketauthserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_credential")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
}
