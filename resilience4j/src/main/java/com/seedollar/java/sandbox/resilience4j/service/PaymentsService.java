package com.seedollar.java.sandbox.resilience4j.service;

import com.seedollar.java.sandbox.resilience4j.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface PaymentsService {

    void payAccount(String source, String target, Double amount);
}
