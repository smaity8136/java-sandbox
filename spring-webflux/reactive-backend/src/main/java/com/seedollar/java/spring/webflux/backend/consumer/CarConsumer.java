package com.seedollar.java.spring.webflux.backend.consumer;

import com.seedollar.java.spring.webflux.backend.domain.dto.CarResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarConsumer {

    Mono<CarResponse> getCar(long id);
    Flux<CarResponse> getCars();
}
