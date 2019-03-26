package com.seedollar.java.spring.webflux.backend.consumer;

import com.seedollar.java.spring.webflux.backend.domain.dto.AccessoryResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccessoryConsumer {

    Mono<AccessoryResponse> getAccessory(long accessoryId);

    Flux<AccessoryResponse> getAccessories();
}
