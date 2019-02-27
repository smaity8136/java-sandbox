package com.seedollar.java.sandbox.resilience4j.service;

public interface MainframeService {

    String syncAccount(String accountId);

    String batchJobs();
}
