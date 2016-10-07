package com.seedollar.java.spring.integration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by seedollar on 10/7/16.
 */
public class SplitterMessageEndpointExampleMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SplitterMessageEndpointExampleMain.class, SplitterMessageEndpointConfiguration.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
