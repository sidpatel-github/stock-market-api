package com.example.stockmarketauthserver.entity;

import jakarta.validation.constraints.Email;
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
    @Email
    private String email;
    private String name;
    private String password;
}
