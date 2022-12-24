package com.javatraining.springboot.carsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CarsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsApiApplication.class, args);
	}

}
