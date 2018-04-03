package com.seedollar.java.sandbox.rest.assured.jsonpath.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class JsonPathSubElement implements Serializable {

    private String description;

    private String category;

    private LocalDateTime timestamp;

    private Integer flagCount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(Integer flagCount) {
        this.flagCount = flagCount;
    }
}
