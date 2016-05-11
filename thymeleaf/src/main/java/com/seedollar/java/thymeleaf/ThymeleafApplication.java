package com.seedollar.java.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Created by seedollar on 5/10/16.
 */
@SpringBootApplication
public class ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication thymeleafApplication = new SpringApplication(ThymeleafApplication.class);
        thymeleafApplication.setApplicationContextClass(AnnotationConfigWebApplicationContext.class);
        thymeleafApplication.run(ThymeleafApplication.class, args);
    }
}
