package com.brokage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.brokage.domain")  // Ensure entity package is scanned
@EnableJpaRepositories(basePackages = "com.brokage.repository")  // Ensure repository package is scanned
@ComponentScan(basePackages = "com.brokage")  // Ensure components (services, controllers) are scanned
public class BrokageApplication {
	public static void main(String[] args) {
		SpringApplication.run(BrokageApplication.class, args);
	}
}