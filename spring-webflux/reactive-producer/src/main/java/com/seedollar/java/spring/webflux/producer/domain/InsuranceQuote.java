package com.seedollar.java.spring.webflux.producer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InsuranceQuote {

    private String classification;

    private double premium;

    private double insuredValue;

}
