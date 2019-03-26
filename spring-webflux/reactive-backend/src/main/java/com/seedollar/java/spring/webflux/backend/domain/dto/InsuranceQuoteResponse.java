package com.seedollar.java.spring.webflux.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceQuoteResponse {

    private String classification;

    private double premium;

    private double insuredValue;

}
