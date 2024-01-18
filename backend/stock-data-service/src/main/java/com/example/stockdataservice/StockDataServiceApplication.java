package com.example.stockdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockDataServiceApplication.class, args);
	}

}
