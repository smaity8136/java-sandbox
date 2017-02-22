package com.seedollar.spring.reactive.web.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

/**
 * Created by seedollar on 2/22/17.
 */
@Configuration
public class PetServiceConfiguration {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://local-mongo");
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient(), "account");
    }


}
