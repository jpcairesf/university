package com.backend.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class UniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

}
