package com.example.stockmarketgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StockMarketGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMarketGatewayApplication.class, args);
	}

}
