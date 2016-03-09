package com.seedollar.java.springamqp;

import com.seedollar.java.springamqp.configuration.QueueConfiguration;
import com.seedollar.java.springamqp.model.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

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
        System.out.println("invoking RPC via convertSendAndReceive() with primitive");
        Object responsePrimitive = rabbitTemplate.convertSendAndReceive(queueConfiguration.rpcDefaultExchange().getName(), "rpc", "test");
        System.out.println("response = " + responsePrimitive);

        System.out.println("invoking RPC via convertSendAndReceive() with POJO");
        Person message = new Person();
        message.setDateOfBirth(new Date());
        message.setFirstName("Jared");
        message.setFirstName("Leto");
        Object responsePojo = rabbitTemplate.convertSendAndReceive(queueConfiguration.rpcDefaultExchange().getName(), "rpc", message);
        System.out.println("response = " + responsePojo);

        System.exit(0);
    }
}
