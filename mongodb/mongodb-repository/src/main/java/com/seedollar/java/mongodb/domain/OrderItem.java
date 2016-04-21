package com.seedollar.java.mongodb.domain;

/**
 * Created by seedollar on 4/21/16.
 */
public class OrderItem {

    private String itemName;

    private Double price;

    private String brandName;

    public OrderItem(String itemName, Double price, String brandName) {
        this.itemName = itemName;
        this.price = price;
        this.brandName = brandName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
