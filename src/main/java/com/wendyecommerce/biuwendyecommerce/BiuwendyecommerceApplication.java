package com.wendyecommerce.biuwendyecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BiuwendyecommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiuwendyecommerceApplication.class, args);
	}

}
