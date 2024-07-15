package com.goat_solucoes.api_servidores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ApiServidoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServidoresApplication.class, args);
	}

}
