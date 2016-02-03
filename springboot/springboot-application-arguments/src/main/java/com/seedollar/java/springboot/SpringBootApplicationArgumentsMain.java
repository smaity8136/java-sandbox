package com.seedollar.java.springboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by seedollar on 2016/02/03.
 */
@SpringBootApplication
public class SpringBootApplicationArgumentsMain implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootApplicationArgumentsMain.class);
        springApplication.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        springApplication.run(args);

        // Run jar with: -DargumenttestOne=1 -DargumenttestTwo=2 --debug logfile.txt debug=true, files=["logfile.txt"] --spring.config.location=test.yml
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("NonOptionArgs: " + args.getNonOptionArgs());
        for (String argSource : args.getSourceArgs()) {
            System.out.println("\nSourceArg: " + argSource);
        }
    }
}
