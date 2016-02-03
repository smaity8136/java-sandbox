package com.seedollar.java.springboot;

import com.seedollar.java.springboot.service.RandomValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by seedollar on 2016/02/03.
 */
@SpringBootApplication
public class SpringBootRandomValuePropertySourceMain implements ApplicationRunner {

    @Autowired
    private RandomValueService randomValueService;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootRandomValuePropertySourceMain.class);
        springApplication.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        springApplication.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        randomValueService.printRandomValues();
    }
}
