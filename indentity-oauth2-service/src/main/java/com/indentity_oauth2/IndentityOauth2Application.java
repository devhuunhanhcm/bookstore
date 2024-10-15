package com.indentity_oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IndentityOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(IndentityOauth2Application.class, args);
	}

}
