package com.seedollar.java.spring.webflux.backend.consumer.impl;

import com.seedollar.java.spring.webflux.backend.common.exception.OrchestratorException;
import com.seedollar.java.spring.webflux.backend.consumer.InsuranceConsumer;
import com.seedollar.java.spring.webflux.backend.domain.dto.InsuranceQuoteResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class InsuranceConsumerImpl implements InsuranceConsumer {

    private WebClient webClient;

    public InsuranceConsumerImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<InsuranceQuoteResponse> getInsuranceQuote(double purchasePrice) {
        return webClient.get()
                .uri(builder -> builder.path("/insurance/quote").queryParam("purchasePrice", purchasePrice).build())
                .retrieve().bodyToMono(InsuranceQuoteResponse.class)
            .doOnError(throwable -> {
                throw new OrchestratorException(HttpStatus.BAD_REQUEST, "Purchase price is too high!");
            });
    }
}
