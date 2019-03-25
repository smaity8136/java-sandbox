package com.seedollar.java.spring.webflux.producer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Accessory {

    private String description;

    private double cost;

}
