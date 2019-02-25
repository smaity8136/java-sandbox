package com.seedollar.java.sandbox.resilience4j.service;

import com.seedollar.java.sandbox.resilience4j.model.Account;

import java.util.List;

public interface AccountService {

    String createAccount();

    List<Account> getAllAccounts();
}
