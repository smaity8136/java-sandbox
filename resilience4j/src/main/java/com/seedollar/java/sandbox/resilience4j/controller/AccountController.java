package com.seedollar.java.sandbox.resilience4j.controller;

import com.seedollar.java.sandbox.resilience4j.service.AccountService;
import com.seedollar.java.sandbox.resilience4j.service.PaymentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;
    private PaymentsService paymentsService;

    public AccountController(AccountService accountService, PaymentsService paymentsService) {
        this.accountService = accountService;
        this.paymentsService = paymentsService;
    }

    @PostMapping("/create/{active}")
    public ResponseEntity<String> createAccount(@PathVariable("active") boolean active) {
        return ResponseEntity.ok(accountService.createAccount(active));
    }

    @PutMapping("/update/{active}")
    public ResponseEntity<String> updateAccount(@PathVariable("active") boolean active) {
        return ResponseEntity.ok(accountService.updateAccount(active));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountId") String accountId) {
        return ResponseEntity.ok(accountService.deleteAccount(accountId));
    }

    @GetMapping
    public ResponseEntity<List> getAllAccounts() {
        return ResponseEntity.ok(this.accountService.getAllAccounts());
    }

    @PostMapping("/resetRequestCount")
    public ResponseEntity<String> resetRequestCount() {
        this.accountService.resetRequestCount();
        return ResponseEntity.ok("Request Count reset back to 50");
    }

    @PostMapping("payments/{sourceAccountId}/{targetAccountId}/{amount}")
    public ResponseEntity<String> makePayment(@PathVariable("sourceAccountId") String sourceAccountId, @PathVariable("targetAccountId") String targetAccountId, @PathVariable("amount") Double amount) {
        paymentsService.payAccount(sourceAccountId, targetAccountId, amount);
        return ResponseEntity.ok("Payment made successfully");
    }
}
