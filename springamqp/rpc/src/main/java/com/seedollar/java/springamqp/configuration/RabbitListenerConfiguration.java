package com.seedollar.java.springamqp.configuration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by seedollar on 2016/03/09.
 */
@Configuration
@EnableRabbit
@Import(RabbitMQConfiguration.class)
public class RabbitListenerConfiguration {

    @RabbitListener(containerFactory = "simpleRabbitListenerContainerFactory", queues = "rpc_default_queue")
    public String rpc(String request) throws InterruptedException {
        Thread.sleep(2000);
        return "rpc response";
    }
}
