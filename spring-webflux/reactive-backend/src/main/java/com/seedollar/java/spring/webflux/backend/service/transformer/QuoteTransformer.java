package com.seedollar.java.spring.webflux.backend.service.transformer;

import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.domain.Quote;
import com.seedollar.java.spring.webflux.backend.domain.dto.InsuranceQuoteResponse;
import org.springframework.stereotype.Component;

@Component
public class QuoteTransformer implements Transformer1<InsuranceQuoteResponse, Quote> {

    @Override
    public Quote apply(InsuranceQuoteResponse insuranceQuoteResponse) {
        return Quote.builder()
                .classification(insuranceQuoteResponse.getClassification())
                .insuredValue(insuranceQuoteResponse.getInsuredValue())
                .premium(insuranceQuoteResponse.getPremium())
                .build();
    }
}
