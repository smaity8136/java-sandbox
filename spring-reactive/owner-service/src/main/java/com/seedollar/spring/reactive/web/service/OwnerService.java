package com.seedollar.spring.reactive.web.service;

import com.seedollar.spring.reactive.domain.Owner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by seedollar on 3/2/17.
 */
public interface OwnerService {

    Mono<Owner> findById(String ownerId);

    Flux<Owner> retieveAllOwners();

    Mono<Owner> retrieveOwnerBySurnameWithPets(String surname);

    Mono<Owner> saveOwner(Mono<Owner> owner);

}
