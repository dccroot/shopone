package com.zyrj.shopone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShoponeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoponeApplication.class, args);
	}

}
