package com.seedollar.spring.reactive.web.controller;

import com.seedollar.spring.reactive.domain.Pet;
import com.seedollar.spring.reactive.web.service.PetService;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by seedollar on 2/22/17.
 */
@RestController
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);

    @Autowired
    private PetService petService;

    @GetMapping("/pet")
    public Flux<Pet> getAllPets() {
        return petService.retrieveAllPets();
    }

    @GetMapping("/pet/owner/{ownerId}")
    public Flux<Pet> findByOwnerId(@PathVariable String ownerId) {
        return petService.findPetsForOwnerId(ownerId).map(p -> new Pet(p.getId(), p.getOwnerId(), p.getName(), p.getBreed(), p.getAge(), p.getBirthDate()));
    }

    @PostMapping("/pet")
    public Mono<Pet> savePet(@RequestBody Publisher<Pet> petStream) {
        return petService.savePet(Mono.from(petStream)
                .map(a -> new Pet(a.getId(), a.getOwnerId(), a.getName(), a.getBreed(), a.getAge(), a.getBirthDate()))
        ).map(a -> new Pet(a.getId(), a.getOwnerId(), a.getName(), a.getBreed(), a.getAge(), a.getBirthDate()));
    }
}
