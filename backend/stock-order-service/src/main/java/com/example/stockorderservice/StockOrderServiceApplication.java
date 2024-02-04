package com.example.stockorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StockOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockOrderServiceApplication.class, args);
	}

}
