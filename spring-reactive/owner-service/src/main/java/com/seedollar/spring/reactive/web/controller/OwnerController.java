package com.seedollar.spring.reactive.web.controller;

import com.seedollar.spring.reactive.domain.Owner;
import com.seedollar.spring.reactive.web.service.OwnerService;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by seedollar on 2/23/17.
 */
@RestController
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner")
    public Flux<Owner> getAllOwners() {
        return ownerService.retieveAllOwners();
    }

    @GetMapping("/owner/{surname}")
    public Mono<Owner> getOwnerBySurnameWithPets(@PathVariable("surname") String surname) {
        return ownerService.retrieveOwnerBySurnameWithPets(surname);
    }

    @PostMapping("/owner")
    public Mono<Owner> saveOwner(@RequestBody Publisher<Owner> ownerStream) {
        return ownerService.saveOwner(Mono.from(ownerStream).map(o -> new Owner(o.getId(), o.getFirstName(), o.getLastName(), o.getRanking()))).map(o -> new Owner(o.getId(), o.getFirstName(), o.getLastName(), o.getRanking()));
    }


}
