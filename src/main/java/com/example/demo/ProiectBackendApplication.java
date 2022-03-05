package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.demo.model"})
@EnableJpaRepositories(basePackages = {"com.example.demo.dao"})
@ComponentScan(basePackages = {"com.example.demo.controllers", "com.example.demo.security"})
public class ProiectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProiectBackendApplication.class, args);
	}

}
