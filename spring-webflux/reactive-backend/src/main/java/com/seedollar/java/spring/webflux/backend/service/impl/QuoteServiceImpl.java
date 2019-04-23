package com.seedollar.java.spring.webflux.backend.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.seedollar.java.spring.webflux.backend.common.exception.OrchestratorException;
import com.seedollar.java.spring.webflux.backend.common.util.MDCLoggingUtil;
import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.consumer.InsuranceConsumer;
import com.seedollar.java.spring.webflux.backend.domain.Quote;
import com.seedollar.java.spring.webflux.backend.domain.dto.InsuranceQuoteResponse;
import com.seedollar.java.spring.webflux.backend.service.QuoteService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class QuoteServiceImpl implements QuoteService {

  private final InsuranceConsumer insuranceConsumer;
  private final Transformer1<InsuranceQuoteResponse, Quote> quoteTransformer;

  public QuoteServiceImpl(InsuranceConsumer insuranceConsumer,
      Transformer1<InsuranceQuoteResponse, Quote> quoteTransformer) {
    this.insuranceConsumer = insuranceConsumer;
    this.quoteTransformer = quoteTransformer;
  }

  @Override
  public Mono<Quote> getQuote(double purchasePrice) {
    return insuranceConsumer.getInsuranceQuote(purchasePrice).map(quoteTransformer)
        .doOnEach(MDCLoggingUtil.logOnError((accessory, throwable) -> log
            .error("Failed to retrieve insurance quote for purchase price: {}", purchasePrice,
                throwable)))
        .doOnEach(MDCLoggingUtil.logOnNext(accessory -> log
            .info("Retrieving insurance quote for purchase price: {}", purchasePrice)));
  }
}
