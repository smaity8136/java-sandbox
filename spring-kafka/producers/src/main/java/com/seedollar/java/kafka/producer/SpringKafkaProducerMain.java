package com.seedollar.java.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by seedollar on 12/30/16.
 */
@SpringBootApplication
public class SpringKafkaProducerMain implements CommandLineRunner {

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringKafkaProducerMain.class);
        springApplication.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int x = 1; x < 100; x++) {
            ListenableFuture<SendResult<Integer, String>> response = kafkaTemplate.send("test", String.format("Number - %s", x));
            // Add a callback method which will tell us the outcome of the listenable future
            response.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("send failed = " + ex);
                }

                @Override
                public void onSuccess(SendResult<Integer, String> result) {
                    System.out.println("send successful = " + result.getProducerRecord().value());
                }
            });
        }
        kafkaTemplate.flush();
    }
}
