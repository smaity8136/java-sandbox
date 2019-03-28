package com.seedollar.java.spring.webflux.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Car {

    private Long carId;

    private String make;

    private String model;

    private String plate;

    private boolean isElectric;

    private double purchasePrice;

    private List<Accessory> accessories;

    private List<Long> accessoryIds;

    private List<Workshop> workshops;

    private List<Long> workshopIds;
}
