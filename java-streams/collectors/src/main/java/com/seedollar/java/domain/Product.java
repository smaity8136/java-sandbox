package com.seedollar.java.domain;

import java.util.concurrent.ThreadLocalRandom;

public class Product {

    private Long id;

    private String name;

    private String description;

    private TargetPrice targetPrice;

    public Product(String name, String description, TargetPrice targetPrice) {
        this.id = ThreadLocalRandom.current().nextLong();
        this.name = name;
        this.description = description;
        this.targetPrice = targetPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TargetPrice getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(TargetPrice targetPrice) {
        this.targetPrice = targetPrice;
    }
}
