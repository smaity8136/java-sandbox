package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.configuration.SpringAOPConfiguration;
import com.seedollar.sandbox.springcore.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAOPApplication implements ApplicationRunner {

    @Autowired
    private Car car;

    public static void main(String[] args) {
        SpringApplication.run(new Object[] {SpringAOPApplication.class, SpringAOPConfiguration.class}, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        car.drive();
        car.reverse();
    }
}
