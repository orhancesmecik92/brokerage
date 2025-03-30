package com.brokerage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.brokerage.domain")
@EnableJpaRepositories(basePackages = "com.brokerage.repository")
@ComponentScan(basePackages = "com.brokerage")
public class BrokerageApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.brokerage.BrokerageApplication.class, args);
	}
}