package com.seedollar.java.spring.integration;

import com.google.common.collect.Lists;
import com.seedollar.java.spring.integration.configuration.ChannelConfiguration;
import com.seedollar.java.spring.integration.domain.Car;
import com.seedollar.java.spring.integration.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

/**
 * Created by seedollar on 9/27/16.
 */
@SpringBootApplication
public class CoreExamplesMain implements CommandLineRunner {

    @Autowired
    ItemService itemService;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CoreExamplesMain.class);
        springApplication.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        ArrayList<Car> cars = Lists.newArrayList(new Car("BMW"), new Car("Audi"));
        itemService.execute(cars);
    }
}
