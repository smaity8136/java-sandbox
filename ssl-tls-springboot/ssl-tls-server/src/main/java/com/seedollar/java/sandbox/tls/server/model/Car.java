package com.seedollar.java.sandbox.tls.server.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Car {

    private long carId;

    private String make;

    private String model;

    private String plate;

    private boolean isElectric;

}
