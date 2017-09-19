package com.seedollar.sandbox.springcore.domain;

public class Container {

    private String name;

    private Integer threadCount;

    public Container(String name, Integer threadCount) {
        this.name = name;
        this.threadCount = threadCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }
}
