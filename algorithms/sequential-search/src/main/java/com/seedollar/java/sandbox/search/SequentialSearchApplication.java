package com.seedollar.java.sandbox.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by seedollar on 11/16/17.
 */
public class SequentialSearchApplication {

    private static final Logger logger = LoggerFactory.getLogger(SequentialSearchApplication.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            logger.error("Please an integer value to be searched.");
            System.exit(-1);
        } else {
            final int[] numbers = {34,56,23,76,23,885,23,5,37,8};
            final int target = Integer.valueOf(args[0]).intValue();

            for (int num : numbers) {
                if (target == num) {
                    logger.info("NUMBER FOUND!");
                    return;
                }
            }
            logger.info("NUMBER NOT FOUND!");
        }
    }
}
