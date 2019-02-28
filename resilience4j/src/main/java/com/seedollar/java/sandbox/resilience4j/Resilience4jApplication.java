package com.seedollar.java.sandbox.resilience4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Resilience4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Resilience4jApplication.class, args);
	}

}
