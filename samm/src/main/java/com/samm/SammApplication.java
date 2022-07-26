package com.samm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SammApplication {

	public static void main(String[] args) {
		SpringApplication.run(SammApplication.class, args);
	}

}
