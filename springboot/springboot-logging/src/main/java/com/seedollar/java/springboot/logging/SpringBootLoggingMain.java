package com.seedollar.java.springboot.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by seedollar on 2016/02/12.
 */
@SpringBootApplication
public class SpringBootLoggingMain implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootLoggingMain.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootLoggingMain.class);
        springApplication.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        springApplication.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.trace("TRACE LEVEL");
        logger.debug("DEBUG LEVEL");
        logger.info("INFO LEVEL");
        logger.warn("WARN LEVEL");
        logger.error("ERROR LEVEL");
    }
}
