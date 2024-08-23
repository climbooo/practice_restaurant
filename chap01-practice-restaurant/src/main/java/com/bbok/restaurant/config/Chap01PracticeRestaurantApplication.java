package com.bbok.restaurant.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bbok.restaurant"})
public class Chap01PracticeRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap01PracticeRestaurantApplication.class, args);
	}

}
