package com.boot.cafemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.boot.cafemanager"})
@EnableJpaRepositories(basePackages = "com.boot.cafemanager.data.jpa.repository")
public class CafeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeManagerApplication.class, args);
	}

}
