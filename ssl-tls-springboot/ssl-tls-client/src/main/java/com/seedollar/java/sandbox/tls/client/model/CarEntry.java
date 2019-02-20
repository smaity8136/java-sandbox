package com.seedollar.java.sandbox.tls.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarEntry {

    private Long carId;
    private String make;
    private String model;
    private String plate;
    private boolean isElectric;
}
