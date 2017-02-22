package com.seedollar.spring.reactive.web.repository;

import com.seedollar.spring.reactive.domain.Pet;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by seedollar on 2/22/17.
 */
@Repository
public class PetRepository {

    private ReactiveMongoTemplate reactiveMongoTemplate;

    public PetRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public Flux<Pet> findAll() {
        return reactiveMongoTemplate.findAll(Pet.class);
    }

    public Flux<Pet> findByOwnerId(String ownerId) {
        return reactiveMongoTemplate.find(query(where("ownerId").is(ownerId)), Pet.class);
    }

    public Mono<Pet> save(Mono<Pet> pet) {
        return reactiveMongoTemplate.insert(pet);
    }
}
