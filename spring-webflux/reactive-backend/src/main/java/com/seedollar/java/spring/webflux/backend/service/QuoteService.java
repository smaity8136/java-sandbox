package com.seedollar.java.spring.webflux.backend.service;

import com.seedollar.java.spring.webflux.backend.domain.Quote;
import reactor.core.publisher.Mono;

public interface QuoteService {

    Mono<Quote> getQuote(double purchasePrice);
}
