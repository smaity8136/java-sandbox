package com.seedollar.spring.reactive.web.service;

import com.seedollar.spring.reactive.domain.Pet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by seedollar on 2/22/17.
 */
public interface PetService {

    Flux<Pet> retrieveAllPets();

    Flux<Pet> findPetsForOwnerId(String ownerId);

    Mono<Pet> savePet(Mono<Pet> pet);


}
