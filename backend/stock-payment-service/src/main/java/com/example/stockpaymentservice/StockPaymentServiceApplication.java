package com.example.stockpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StockPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPaymentServiceApplication.class, args);
	}

}
