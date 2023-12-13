package com.keselik.loancalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.keselik.loancalculator")
public class LoanApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}
}
