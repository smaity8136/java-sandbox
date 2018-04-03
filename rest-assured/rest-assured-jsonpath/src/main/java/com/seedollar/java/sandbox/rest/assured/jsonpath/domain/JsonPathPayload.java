package com.seedollar.java.sandbox.rest.assured.jsonpath.domain;

import java.io.Serializable;
import java.util.List;

public class JsonPathPayload implements Serializable {

    private String name;

    private String comment;

    private boolean isActive;

    private Double cost;

    private List<JsonPathSubElement> elements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<JsonPathSubElement> getElements() {
        return elements;
    }

    public void setElements(List<JsonPathSubElement> elements) {
        this.elements = elements;
    }
}
