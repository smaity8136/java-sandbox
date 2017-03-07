package com.seedollar.spring.reactive.web.repository;

import com.seedollar.spring.reactive.domain.Owner;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by seedollar on 3/2/17.
 */
@Repository
public class OwnerRepository {

    private ReactiveMongoTemplate reactiveMongoTemplate;

    public OwnerRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public Mono<Owner> findByOwnerId(String ownerId) {
        return reactiveMongoTemplate.findById(ownerId, Owner.class);
    }

    public Mono<Owner> findBySurname(String surname) {
        return reactiveMongoTemplate.findOne(query(where("surname").is(surname)), Owner.class);
    }

    public Flux<Owner> findAll() {
        return reactiveMongoTemplate.findAll(Owner.class);
    }

    public Mono<Owner> save(Mono<Owner> owner) {
        return reactiveMongoTemplate.insert(owner);
    }


}
