package com.seedollar.java.sandbox.resilience4j.service.impl;

import com.seedollar.java.sandbox.resilience4j.exception.AccountCreationException;
import com.seedollar.java.sandbox.resilience4j.model.Account;
import com.seedollar.java.sandbox.resilience4j.model.enumeration.AccountTypeEnum;
import com.seedollar.java.sandbox.resilience4j.service.AccountService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final CircuitBreaker createAccountCircuitBreaker;
    private final RateLimiter getAllAccountsRateLimiter;
    private Map<String, Account> accountsMap = new ConcurrentHashMap<>();

    private CountDownLatch requestCounter = new CountDownLatch(50);

    public AccountServiceImpl(CircuitBreaker createAccountCircuitBreaker, RateLimiter getAllAccountsRateLimiter) {
        this.createAccountCircuitBreaker = createAccountCircuitBreaker;
        this.getAllAccountsRateLimiter = getAllAccountsRateLimiter;
    }

    @Override
    public String createAccount() {
        logger.info("Request COUNT: " + requestCounter.getCount());
        CheckedFunction0<Account> createNewAccountDecoratorFunction = CircuitBreaker.decorateCheckedSupplier(createAccountCircuitBreaker, this::createNewAccount);
        return Try.of(createNewAccountDecoratorFunction).map(Account::getAccountId)
                .recover(ex -> "Attempting recovery....")
                .getOrElseThrow(
                ex -> {
                    createAccountCircuitBreaker.onError(0, ex);
                    return new AccountCreationException(String.format("Account creation failed. Circuit Breaker status: %s", createAccountCircuitBreaker.getState().name()), ex);
                });
    }

    @Override
    public List<Account> getAllAccounts() {
        logger.info("Request COUNT: {} | RateLimiter is {} requests per {}", requestCounter.getCount(), getAllAccountsRateLimiter.getRateLimiterConfig().getLimitForPeriod(), getAllAccountsRateLimiter.getRateLimiterConfig().getLimitRefreshPeriod());
        CheckedFunction0<ArrayList> getAllAccountsTry = RateLimiter.decorateCheckedSupplier(this.getAllAccountsRateLimiter, () -> new ArrayList(accountsMap.values()));

        // When the countdown latch reaches zero, we want to dynamically update the request limit to 100 requests per second.
        if (requestCounter.getCount() == 0) {
            getAllAccountsRateLimiter.changeLimitForPeriod(100);
        }
        return Try.of(getAllAccountsTry)
                .onSuccess(result -> requestCounter.countDown())
                .onFailure(throwable -> logger.info("You're going to fast! Slow down......"))
                .get();
    }

    @Override
    public boolean resetRequestCount() {
        requestCounter = new CountDownLatch(50);
        return true;
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
            newAccount.setDescription(createAccountCircuitBreaker.getState().name()); // Record the circuit breaker state so that we know how this account was created
            accountsMap.put(newAccount.getAccountId(), newAccount);
            createAccountCircuitBreaker.onSuccess(0);
            return newAccount;
        }
    }
}
