package com.seedollar.java.thymeleaf.domain;

import java.util.List;

/**
 * Created by seedollar on 5/11/16.
 */
public class Feature {

    private String featureName;
    private List<FeatureItem> featureItems;

    public Feature(String featureName) {
        this.featureName = featureName;
    }
}
