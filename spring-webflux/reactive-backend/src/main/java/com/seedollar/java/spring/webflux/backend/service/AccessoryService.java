package com.seedollar.java.spring.webflux.backend.service;

import com.seedollar.java.spring.webflux.backend.domain.Accessory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccessoryService {

    Mono<Accessory> getAccessory(long accessoryId);

    Flux<Accessory> getAccessories();
}
