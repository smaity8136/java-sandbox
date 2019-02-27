package com.seedollar.java.sandbox.resilience4j.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Branch implements Serializable {

    private String branchId;
    private String name;
    private boolean open;
}
