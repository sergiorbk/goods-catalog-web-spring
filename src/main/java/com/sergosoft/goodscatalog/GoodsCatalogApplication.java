package com.sergosoft.goodscatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class GoodsCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsCatalogApplication.class, args);
	}
}
