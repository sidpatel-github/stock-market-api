package com.example.stockdataservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Autowired
    private ApiProperties apiProperties;
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiProperties.getUrl())
                .build();
    }
}
