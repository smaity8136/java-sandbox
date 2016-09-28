package com.seedollar.java.spring.integration.configuration;

import com.seedollar.java.spring.integration.domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;

import java.util.Comparator;

/**
 * Created by seedollar on 9/28/16.
 */
@Configuration
@EnableIntegration
@Import(GlobalChannelInterceptorConfiguration.class)
public class PriorityChannelConfiguration {

    @Bean
    public PriorityChannel priorityChannel() {
        PriorityChannel priorityChannel = new PriorityChannel(5, new Comparator<Message<?>>() {
            @Override
            public int compare(Message<?> message1, Message<?> message2) {
                Car car1 = (Car) message1.getPayload();
                Car car2 = (Car) message2.getPayload();

                if (car1.getRanking() > car2.getRanking()) {
                    return 1;
                } else if (car1.getRanking() < car2.getRanking()) {
                    return -1;
                }
                return 0;
            }
        });

        priorityChannel.setDatatypes(Car.class);
        return priorityChannel;
    }
}
