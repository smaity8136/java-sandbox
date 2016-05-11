package com.seedollar.java.thymeleaf.controller;

import com.seedollar.java.thymeleaf.domain.Feature;

import java.util.List;
import java.util.Set;

/**
 * Created by seedollar on 5/11/16.
 */
public class FeaturesForm {

    private Set<String> availableFeatures;

    private List<Feature> allocatedFeatures;

    private Set<String> availableItems;

    private List<Feature> allocatedItems;

    public FeaturesForm(Set<String> availableFeatures, Set<String> availableItems) {
        this.availableFeatures = availableFeatures;
        this.availableItems = availableItems;
    }

    public Set<String> getAvailableFeatures() {
        return availableFeatures;
    }

    public void setAvailableFeatures(Set<String> availableFeatures) {
        this.availableFeatures = availableFeatures;
    }

    public List<Feature> getAllocatedFeatures() {
        return allocatedFeatures;
    }

    public void setAllocatedFeatures(List<Feature> allocatedFeatures) {
        this.allocatedFeatures = allocatedFeatures;
    }
}
