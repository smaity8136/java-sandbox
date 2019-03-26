package com.seedollar.java.spring.webflux.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OTP {

    private Car car;

    private Quote quote;

    private Double totalAmount;

}
