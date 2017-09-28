package com.seedollar.sandbox.springcore.domain;

public class Weapon {

    private Long id;

    private String name;

    private String description;

    private int damageIndicator;

    private Float price;

    private boolean inStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getDamageIndicator() {
        return damageIndicator;
    }

    public void setDamageIndicator(int damageIndicator) {
        this.damageIndicator = damageIndicator;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
