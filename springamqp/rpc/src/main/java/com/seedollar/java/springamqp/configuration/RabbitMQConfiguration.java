package com.seedollar.java.springamqp.configuration;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by seedollar on 2016/03/07.
 */
@Configuration
@PropertySource(value = "classpath:rabbitmq.properties")
public class RabbitMQConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public ConnectionFactory rabbitMQConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(environment.getProperty("rabbitmq.host"));
        connectionFactory.setPort(Integer.parseInt(environment.getProperty("rabbitmq.port")));
        connectionFactory.setUsername(environment.getProperty("rabbitmq.username"));
        connectionFactory.setPassword(environment.getProperty("rabbitmq.password"));

        // Check if a virtualhost property is set, and if so we configure the factory accordingly
        String vhost = environment.getProperty("rabbitmq.virtualhost");
        if (vhost != null && !vhost.isEmpty()) {
            connectionFactory.setVirtualHost(vhost);
        }
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(rabbitMQConnectionFactory());
        simpleRabbitListenerContainerFactory.setMessageConverter(jackson2MessageConverter());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitMQConnectionFactory());
        rabbitTemplate.setMessageConverter(jackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(rabbitMQConnectionFactory());
    }

    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
