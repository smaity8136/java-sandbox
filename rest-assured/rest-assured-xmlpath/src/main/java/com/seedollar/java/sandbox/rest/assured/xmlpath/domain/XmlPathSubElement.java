package com.seedollar.java.sandbox.rest.assured.xmlpath.domain;

import java.io.Serializable;

public class XmlPathSubElement implements Serializable {

    private boolean isEnabled;

    private LevelEnumeration level;

    private int counter;

    private Double price;

    private Double tax;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public LevelEnumeration getLevel() {
        return level;
    }

    public void setLevel(LevelEnumeration level) {
        this.level = level;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
}
