package com.seedollar.java.domain;

import java.util.concurrent.ThreadLocalRandom;

public class Product {

    private Long id;

    private String name;

    private String description;

    private Price price;

    public Product(String name, String description, Price price) {
        this.id = ThreadLocalRandom.current().nextLong();
        this.name = name;
        this.description = description;
        this.price = price;
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

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price.toString() +
                '}';
    }
}
