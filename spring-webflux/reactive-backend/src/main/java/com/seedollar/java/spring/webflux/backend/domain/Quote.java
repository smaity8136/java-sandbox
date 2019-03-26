package com.seedollar.java.spring.webflux.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Quote {

    private String classification;

    private double premium;

    private double insuredValue;

}
