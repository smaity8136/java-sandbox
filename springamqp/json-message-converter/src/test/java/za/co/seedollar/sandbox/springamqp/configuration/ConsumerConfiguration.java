package za.co.seedollar.sandbox.springamqp.configuration;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.co.seedollar.sandbox.springamqp.listener.CustomerMessageListener;

/**
 * Created by seedollar on 7/28/17.
 */
@Configuration
@Import(RabbitConfiguration.class)
@ComponentScan("za.co.seedollar.sandbox.springamqp.listener")
public class ConsumerConfiguration {

    @Autowired
    RabbitConfiguration rabbitConfiguration;

    @Bean
    public SimpleMessageListenerContainer customerSimpleMessageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(rabbitConfiguration.rabbitMQConnectionFactory());
        simpleMessageListenerContainer.setQueueNames(rabbitConfiguration.customerQueue().getName());
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new CustomerMessageListener());
        messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        return simpleMessageListenerContainer;
    }
}
