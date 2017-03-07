package com.seedollar.spring.reactive.web.service.impl;

import com.seedollar.spring.reactive.domain.Owner;
import com.seedollar.spring.reactive.domain.Pet;
import com.seedollar.spring.reactive.web.repository.OwnerRepository;
import com.seedollar.spring.reactive.web.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Created by seedollar on 3/2/17.
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

    private OwnerRepository ownerRepository;
    private WebClient webClient;

    public OwnerServiceImpl(OwnerRepository ownerRepository, WebClient webClient) {
        this.ownerRepository = ownerRepository;
        this.webClient = webClient;
    }

    @Override
    public Mono<Owner> findById(String ownerId) {
        return ownerRepository.findByOwnerId(ownerId).map(o -> new Owner(o.getId(), o.getFirstName(), o.getLastName(), o.getRanking()));
    }

    @Override
    public Flux<Owner> retieveAllOwners() {
        return ownerRepository.findAll().map(o -> new Owner(o.getId(), o.getFirstName(), o.getLastName(), o.getRanking()));
    }

    @Override
    public Mono<Owner> retrieveOwnerBySurnameWithPets(String surname) {
        return ownerRepository.findBySurname(surname).log().flatMap(owner -> webClient.get().uri("/pet/owner/{ownerId}").accept(MediaType.APPLICATION_JSON).exchange().log().flatMap(response -> response.bodyToFlux(Pet.class))).collectList().map(pets -> {return new Owner(surname, pets);});
    }

    @Override
    public Mono<Owner> saveOwner(Mono<Owner> owner) {
        logger.info("Saving Owner....");
        return ownerRepository.save(owner);
    }
}
