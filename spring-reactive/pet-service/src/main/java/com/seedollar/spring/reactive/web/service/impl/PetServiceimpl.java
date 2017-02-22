package com.seedollar.spring.reactive.web.service.impl;

import com.seedollar.spring.reactive.domain.Pet;
import com.seedollar.spring.reactive.web.repository.PetRepository;
import com.seedollar.spring.reactive.web.service.PetService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by seedollar on 2/22/17.
 */
@Service
public class PetServiceimpl implements PetService {

    private PetRepository petRepository;

    public PetServiceimpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Flux<Pet> retrieveAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Flux<Pet> findPetsForOwnerId(String ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    @Override
    public Mono<Pet> savePet(Mono<Pet> pet) {
        return petRepository.save(pet);
    }


}
