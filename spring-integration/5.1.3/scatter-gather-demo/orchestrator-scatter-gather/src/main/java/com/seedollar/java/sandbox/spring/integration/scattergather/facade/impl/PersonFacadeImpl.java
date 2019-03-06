package com.seedollar.java.sandbox.spring.integration.scattergather.facade.impl;

import com.seedollar.java.sandbox.spring.integration.scattergather.domain.Person;
import com.seedollar.java.sandbox.spring.integration.scattergather.facade.PersonFacade;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class PersonFacadeImpl implements PersonFacade {

    private MessagingTemplate getPersonMessagingTemplate;

    public PersonFacadeImpl(MessagingTemplate getPersonMessagingTemplate) {
        this.getPersonMessagingTemplate = getPersonMessagingTemplate;
    }

    @Override
    public Person getPerson(String personId) {
        StopWatch watch = new StopWatch("testGetPerson");
        watch.start();
        Message<?> message = getPersonMessagingTemplate.sendAndReceive(MessageBuilder.withPayload(personId).build());
        watch.stop();
        Person person = (Person) message.getPayload();
        person.setDuration(watch.getTotalTimeMillis());
        return person;
    }
}
