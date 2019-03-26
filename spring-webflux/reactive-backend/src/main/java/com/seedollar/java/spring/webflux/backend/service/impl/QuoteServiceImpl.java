package com.seedollar.java.spring.webflux.backend.service.impl;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.consumer.InsuranceConsumer;
import com.seedollar.java.spring.webflux.backend.domain.Quote;
import com.seedollar.java.spring.webflux.backend.domain.dto.InsuranceQuoteResponse;
import com.seedollar.java.spring.webflux.backend.service.QuoteService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final InsuranceConsumer insuranceConsumer;
    private final Transformer1<InsuranceQuoteResponse, Quote> quoteTransformer;

    public QuoteServiceImpl(InsuranceConsumer insuranceConsumer, Transformer1<InsuranceQuoteResponse, Quote> quoteTransformer) {
        this.insuranceConsumer = insuranceConsumer;
        this.quoteTransformer = quoteTransformer;
    }

    @Override
    public Mono<Quote> getQuote(double purchasePrice) {
        return insuranceConsumer.getInsuranceQuote(purchasePrice).map(quoteTransformer);
    }
}
