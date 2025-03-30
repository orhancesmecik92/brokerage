package com.brokage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.brokage.domain")
@EnableJpaRepositories(basePackages = "com.brokage.repository")
@ComponentScan(basePackages = "com.brokage")
public class BrokageApplication {
	public static void main(String[] args) {
		SpringApplication.run(BrokageApplication.class, args);
	}
}
