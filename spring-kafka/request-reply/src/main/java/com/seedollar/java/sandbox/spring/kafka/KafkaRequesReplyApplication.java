package com.seedollar.java.sandbox.spring.kafka;

import com.seedollar.java.sandbox.spring.kafka.configuration.KafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaRequesReplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[] {KafkaRequesReplyApplication.class, KafkaConfiguration.class}, args);
    }
}
