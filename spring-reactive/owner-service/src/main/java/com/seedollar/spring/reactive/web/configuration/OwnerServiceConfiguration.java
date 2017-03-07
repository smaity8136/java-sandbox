package com.seedollar.spring.reactive.web.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by seedollar on 2/23/17.
 */
@Configuration
public class OwnerServiceConfiguration {


    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://local-mongo");
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient(), "test");
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector()).baseUrl("http://localhost:8047").build();
    }
}
