package com.wellington.barbershopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BarbershopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarbershopApiApplication.class, args);
	}

}
