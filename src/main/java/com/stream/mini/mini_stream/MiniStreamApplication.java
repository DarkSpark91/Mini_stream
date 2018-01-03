package com.stream.mini.mini_stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class MiniStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniStreamApplication.class, args);
	}
}
