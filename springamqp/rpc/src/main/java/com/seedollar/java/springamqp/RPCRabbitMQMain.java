package com.seedollar.java.springamqp;

import com.seedollar.java.springamqp.configuration.QueueConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by seedollar on 2016/03/09.
 */
@SpringBootApplication
@ComponentScan("com.seedollar.java.springamqp")
public class RPCRabbitMQMain implements ApplicationRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private QueueConfiguration queueConfiguration;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RPCRabbitMQMain.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        application.run(args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("invoking RPC......");
        Object response = rabbitTemplate.convertSendAndReceive(queueConfiguration.rpcDefaultExchange().getName(), "rpc", "test");
        System.out.println("response = " + response);
    }
}
