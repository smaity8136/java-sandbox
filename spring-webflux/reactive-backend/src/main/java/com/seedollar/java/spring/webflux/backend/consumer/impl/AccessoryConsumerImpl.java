package com.seedollar.java.spring.webflux.backend.consumer.impl;

import com.seedollar.java.spring.webflux.backend.consumer.AccessoryConsumer;
import com.seedollar.java.spring.webflux.backend.domain.dto.AccessoryResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AccessoryConsumerImpl implements AccessoryConsumer {

    private WebClient webClient;

    public AccessoryConsumerImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<AccessoryResponse> getAccessory(long accessoryId) {
        return webClient.get()
                .uri("/accessories/{accessoryId}", accessoryId)
                .retrieve().bodyToMono(AccessoryResponse.class);
    }

    @Override
    public Flux<AccessoryResponse> getAccessories() {
        return webClient.get()
                .uri("/accessories")
                .retrieve().bodyToFlux(AccessoryResponse.class);
    }
}
