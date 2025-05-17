package com.ezpark.web_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EzparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzparkApplication.class, args);
	}

}
