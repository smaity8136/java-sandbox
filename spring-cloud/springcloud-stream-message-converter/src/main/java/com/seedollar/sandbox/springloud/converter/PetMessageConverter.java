package com.seedollar.sandbox.springloud.converter;

import com.google.common.collect.Lists;
import io.vavr.control.Try;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;
import com.seedollar.sandbox.springloud.domain.Cat;
import com.seedollar.sandbox.springloud.domain.Dog;
import com.seedollar.sandbox.springloud.domain.Parrot;
import com.seedollar.sandbox.springloud.domain.Pet;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

/**
 * Created by seedollar on 7/5/17.
 */
public class PetMessageConverter extends AbstractMessageConverter {

    public PetMessageConverter() {
        super(Lists.newArrayList(new MimeType("application", "Dog"),
                new MimeType("application", "Cat"),
                new MimeType("application", "Parrot")));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return (clazz == Pet.class);
    }

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        Object payloadInstance = message.getPayload();

        return Try.of(() -> Match(payloadInstance).of(
                Case($(instanceOf(Dog.class)), () -> {
                            Dog dogInstance = (Dog) message.getPayload();
                            return new Pet(dogInstance.getName(), dogInstance.getColor(), "BARK", Dog.class.getSimpleName());
                        }),
                        Case($(instanceOf(Cat.class)), () -> {
                            Cat catInstance = (Cat) message.getPayload();
                            return new Pet(catInstance.getName(), "GINGER", "MEOW", Cat.class.getSimpleName());
                        }),
                        Case($(instanceOf(Parrot.class)), () -> {
                            Parrot parrotInstance = (Parrot) message.getPayload();
                            return new Pet("BIRDIE", parrotInstance.getColor(), "CHIRP", Parrot.class.getSimpleName());
                        })
                )).get();
    }
}
