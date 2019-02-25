package com.seedollar.java.sandbox.resilience4j.service.impl;

import com.seedollar.java.sandbox.resilience4j.exception.AccountCreationException;
import com.seedollar.java.sandbox.resilience4j.model.Account;
import com.seedollar.java.sandbox.resilience4j.model.enumeration.AccountTypeEnum;
import com.seedollar.java.sandbox.resilience4j.service.AccountService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountServiceImpl implements AccountService {

    private Map<String, Account> accountsMap = new ConcurrentHashMap<>();
    private CircuitBreaker circuitBreaker;

    public AccountServiceImpl(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    @Override
    public String createAccount() {

        CheckedFunction0<Account> createNewAccountDecoratorFunction = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, this::createNewAccount);
        return Try.of(createNewAccountDecoratorFunction).map(Account::getAccountId).getOrElseThrow(
                ex -> {
                    circuitBreaker.onError(0, ex);
                    return new AccountCreationException(String.format("Account creation failed. Circuit Breaker status: %s", circuitBreaker.getState().name()), ex);
                });
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accountsMap.values());
    }

    private Account createNewAccount() {
        boolean fail = ThreadLocalRandom.current().nextBoolean();
        if (fail) {
            throw new RuntimeException("Mock failure");
        } else {
            Account newAccount = new Account();
            newAccount.setAccountId(UUID.randomUUID().toString());
            newAccount.setCreatedDate(LocalDate.now());
            newAccount.setAccountType(AccountTypeEnum.values()[(ThreadLocalRandom.current().nextInt(0, AccountTypeEnum.values().length))]);
            accountsMap.put(newAccount.getAccountId(), newAccount);
            circuitBreaker.onSuccess(0);
            return newAccount;
        }
    }
}
