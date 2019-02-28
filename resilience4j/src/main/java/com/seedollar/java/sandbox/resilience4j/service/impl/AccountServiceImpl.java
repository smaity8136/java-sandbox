package com.seedollar.java.sandbox.resilience4j.service.impl;

import com.seedollar.java.sandbox.resilience4j.exception.AccountCreationException;
import com.seedollar.java.sandbox.resilience4j.model.Account;
import com.seedollar.java.sandbox.resilience4j.model.enumeration.AccountTypeEnum;
import com.seedollar.java.sandbox.resilience4j.service.AccountService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    private final CircuitBreaker deleteAccountCircuitBreaker;
    private final RateLimiter getAllAccountsRateLimiter;
    private Map<String, Account> accountsMap = new ConcurrentHashMap<>();

    private CountDownLatch requestCounter = new CountDownLatch(50);

    public AccountServiceImpl(CircuitBreakerRegistry circuitBreakerRegistry, CircuitBreaker deleteAccountCircuitBreaker, RateLimiter getAllAccountsRateLimiter) {
        this.deleteAccountCircuitBreaker = deleteAccountCircuitBreaker;
        this.getAllAccountsRateLimiter = getAllAccountsRateLimiter;
        this.createAccountCircuitBreaker = circuitBreakerRegistry.circuitBreaker("createAccount");
        this.createAccountCircuitBreaker.getEventPublisher()
                .onStateTransition(evt -> logger.info("CIRCUIT BREAKER transition from {} to {}", evt.getStateTransition().getFromState(), evt.getStateTransition().getToState()));

        this.deleteAccountCircuitBreaker.getEventPublisher()
                .onError(evt -> logger.info("Delete Account Circuit Breaker FAILED"))
                .onSuccess(evt -> logger.info("Delete Account Circuit Breaker SUCCEEDED"))
                .onStateTransition(evt -> logger.info("CIRCUIT BREAKER transition from {} to {}", evt.getStateTransition().getFromState(), evt.getStateTransition().getToState()));
    }

    @Override
    public String createAccount(boolean active) {
        CheckedFunction0<Account> createNewAccountDecoratorFunction = CircuitBreaker.decorateCheckedSupplier(createAccountCircuitBreaker, () -> createNewAccount(active));
        return Try.of(createNewAccountDecoratorFunction).map(Account::getAccountId)
                .getOrElseThrow(
                        ex -> new AccountCreationException(String.format("Account creation failed. Circuit Breaker status: %s", createAccountCircuitBreaker.getState().name()), ex));
    }

    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "updateAccount")
    @Override
    public String updateAccount(boolean active) {
        if (!active) {
            throw new RuntimeException("Update Account Mock failure");
        } else {
            return "Account update successful";
        }
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

    @Override
    public String deleteAccount(String accountId) {
        CheckedFunction0<String> deleteAccountFunction = CircuitBreaker.decorateCheckedSupplier(deleteAccountCircuitBreaker, () -> {
            if (accountId.equalsIgnoreCase("fail")) {
                throw new RuntimeException("Delete Account Mock failure");
            }
            return "Account Deleted Successfully";
        });
        return Try.of(deleteAccountFunction).get();
    }


    private Account createNewAccount(boolean active) {
        if (!active) {
            throw new RuntimeException("Create Account Mock failure");
        } else {
            Account newAccount = new Account();
            newAccount.setAccountId(UUID.randomUUID().toString());
            newAccount.setCreatedDate(LocalDate.now());
            newAccount.setAccountType(AccountTypeEnum.values()[(ThreadLocalRandom.current().nextInt(0, AccountTypeEnum.values().length))]);
            newAccount.setDescription(createAccountCircuitBreaker.getState().name()); // Record the circuit breaker state so that we know how this account was created
            accountsMap.put(newAccount.getAccountId(), newAccount);
            return newAccount;
        }
    }
}
