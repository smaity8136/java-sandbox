package com.seedollar.java.springboot.configuration.settings;

import java.util.List;

/**
 * Created by seedollar on 2016/02/12.
 */
public abstract class ConfigSettings {

    private String designation;

    private List<String> aliases;

    public String getDesignation() {
        return designation;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}
