package com.seedollar.java.sandbox.resilience4j.controller;

import com.seedollar.java.sandbox.resilience4j.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount() {
        return ResponseEntity.ok(accountService.createAccount());
    }

    @GetMapping
    public ResponseEntity<List> getAllAccounts() {
        return ResponseEntity.ok(this.accountService.getAllAccounts());
    }
}
