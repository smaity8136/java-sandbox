package com.seedollar.java.spring.webflux.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long carId;

    private String make;

    private String model;

    private String plate;

    private boolean isElectric;

    private double purchasePrice;

    private List<Long> accessoryIds;

    private List<Long> workshopIds;
}
