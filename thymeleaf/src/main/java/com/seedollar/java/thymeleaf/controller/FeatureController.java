package com.seedollar.java.thymeleaf.controller;

import com.seedollar.java.thymeleaf.configuration.DefaultPropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by seedollar on 5/11/16.
 */
@Controller
public class FeatureController {

    @Autowired
    private DefaultPropertiesConfiguration defaultPropertiesConfiguration;

    @RequestMapping("/")
    public String showFeaturesPage(Model model) {

        Set<String> selectedFeatures = new HashSet<>();
        selectedFeatures.add("Toilet");
        selectedFeatures.add("Garden");
        selectedFeatures.add("Balcony");

        Set<String> selectedItems = new HashSet<>();
        selectedFeatures.add("Taps");
        selectedFeatures.add("Lights");

        Set<String> consolidateAvailableFeatures = consolidateAvailable(defaultPropertiesConfiguration.getDefaultFeatures(), selectedFeatures);
        Set<String> consolidateAvailableItems = consolidateAvailable(defaultPropertiesConfiguration.getDefaultItems(), selectedItems);

        model.addAttribute(new FeaturesForm(consolidateAvailableFeatures, consolidateAvailableItems));

        return "view-features";

    }

    private Set<String> consolidateAvailable(List<String> available, Set<String> selected) {
        Set<String> consolidated = new HashSet<>();
        available.removeAll(selected);
        Collections.addAll(consolidated, available.toArray(new String[] {}));
        return consolidated;
    }
}
