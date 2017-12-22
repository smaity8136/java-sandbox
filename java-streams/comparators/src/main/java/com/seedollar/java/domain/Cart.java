package com.seedollar.java.domain;

public class Cart {

    private Double discountedAmount;

    private Double taxedAmount;

    private Double subTotalAmount;

    private Double totalAmount;

    public Cart() {
        this.discountedAmount = 0.0d;
        taxedAmount = 0.0;
        subTotalAmount = 0.0;
        totalAmount = 0.0;
    }

    public Cart(Double discountedAmount, Double taxedAmount, Double subTotalAmount, Double totalAmount) {
        this.discountedAmount = discountedAmount;
        this.taxedAmount = taxedAmount;
        this.subTotalAmount = subTotalAmount;
        this.totalAmount = totalAmount;
    }

    public Double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public Double getTaxedAmount() {
        return taxedAmount;
    }

    public void setTaxedAmount(Double taxedAmount) {
        this.taxedAmount = taxedAmount;
    }

    public Double getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(Double subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "discountedAmount=" + discountedAmount +
                ", taxedAmount=" + taxedAmount +
                ", subTotalAmount=" + subTotalAmount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
