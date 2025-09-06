package com.coffee.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) {
		System.out.println("Hello Na-chan from Admin");
		SpringApplication.run(AdminApplication.class, args);
	}

}
