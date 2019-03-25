package com.seedollar.java.spring.webflux.producer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class Car {

    private Long carId;

    private String make;

    private String model;

    private String plate;

    private boolean isElectric;

    private List<Long> accessoryIds;
}
