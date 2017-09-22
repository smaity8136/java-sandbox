package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.configuration.BeanScopingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanScopingSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Object[] {BeanScopingSpringApplication.class, BeanScopingConfiguration.class}, args);
    }
}
