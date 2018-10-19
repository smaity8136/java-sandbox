package com.seedollar.java.domain;

import java.util.concurrent.ThreadLocalRandom;

public class TargetPrice {

    private Long id;

    private Double actualPrice;

    private Double discountRate;

    private Double taxRate;

    private TargetPrice.PriceType priceType;

    public TargetPrice() {}

    public TargetPrice(Double actualPrice, Double discountRate, Double taxRate, PriceType priceType) {
        this.id = ThreadLocalRandom.current().nextLong();
        this.actualPrice = actualPrice;
        this.discountRate = discountRate;
        this.taxRate = taxRate;
        this.priceType = priceType;
    }

    public Long getId() {
        return id;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public enum PriceType {
        COST, SALE;
    }
}
