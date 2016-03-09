package com.seedollar.java.springamqp.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by seedollar on 2016/03/09.
 */
@Configuration
@PropertySource("classpath:messaging.properties")
@Import(RabbitMQConfiguration.class)
public class QueueConfiguration {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private Environment environment;

    @Bean
    public Exchange rpcDefaultExchange() {
        Exchange rpcDefaultExchange = new DirectExchange(environment.getProperty("rpc.default.exchange"), true, false);
        return rpcDefaultExchange;
    }

    @Bean
    public Queue rpcDefaultQueue() {
        return new Queue(environment.getProperty("rpc.default.queue"), true, false, false);
    }

    @Bean
    public Binding binding_rpcDefaultQueue_to_rpcDefaultExchange() {
        return BindingBuilder.bind(rpcDefaultQueue()).to(rpcDefaultExchange()).with("rpc").noargs();
    }

}
