package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.configuration.ConditionalBeansConfiguration;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConditionalBeansSpringApplication implements ApplicationRunner {

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{ConditionalBeansConfiguration.class, ConditionalBeansSpringApplication.class}, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Try.of(() -> {
            Object capturedEmployee = applicationContext.getBean("capturedEmployee");
            if (capturedEmployee != null) {
                System.out.println("capturedEmployee = " + capturedEmployee);
            }
            return true;
        });

        System.out.println("employee = " + applicationContext.getBean("normalEmployee").toString());
    }
}
