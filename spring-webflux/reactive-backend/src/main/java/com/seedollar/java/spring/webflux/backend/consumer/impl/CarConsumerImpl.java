package com.seedollar.java.spring.webflux.backend.consumer.impl;

import com.seedollar.java.spring.webflux.backend.consumer.CarConsumer;
import com.seedollar.java.spring.webflux.backend.domain.dto.CarResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CarConsumerImpl implements CarConsumer {

    private WebClient webClient;

    public CarConsumerImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<CarResponse> getCar(long id) {
        return webClient.get()
                .uri("/cars/{carId}", id)
                .retrieve().bodyToMono(CarResponse.class);
    }

    @Override
    public Flux<CarResponse> getCars() {
        return webClient.get()
                .uri("/cars")
                .retrieve().bodyToFlux(CarResponse.class);
    }
}
