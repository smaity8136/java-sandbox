package com.seedollar.java.spring.webflux.backend.consumer;

import com.seedollar.java.spring.webflux.backend.domain.dto.InsuranceQuoteResponse;
import reactor.core.publisher.Mono;

public interface InsuranceConsumer {

    Mono<InsuranceQuoteResponse> getInsuranceQuote(double purchasePrice);
}
