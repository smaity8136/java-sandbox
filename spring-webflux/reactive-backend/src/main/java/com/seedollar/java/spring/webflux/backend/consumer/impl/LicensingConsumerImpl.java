package com.seedollar.java.spring.webflux.backend.consumer.impl;

import com.seedollar.java.spring.webflux.backend.consumer.LicensingConsumer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class LicensingConsumerImpl implements LicensingConsumer {

    private WebClient webClient;

    public LicensingConsumerImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Boolean> validLicense(String ssn) {
        return webClient.get()
                .uri("/licensing/{ssn}", ssn)
                .retrieve().bodyToMono(Boolean.class);
    }
}
