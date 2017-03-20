package com.seedollar.spring.ldap.controller;

import com.seedollar.spring.ldap.domain.LdapUser;
import com.seedollar.spring.ldap.service.LdapService;
import javaslang.control.Try;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javaslang.API.*;

/**
 * Created by seedollar on 3/14/17.
 */
@RestController
public class LdapRestController {

    private LdapService ldapService;

    public LdapRestController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LdapUser ldapUser) {
        return Try.of(() -> {
            boolean authenticated = ldapService.authenticate(ldapUser.getUsername(), ldapUser.getPassword());

            if (authenticated) {
                return ResponseEntity.ok("User authenticated successfully");
            } else {
                return ResponseEntity.ok("User failed authentication");
            }
        }).onFailure(throwable -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()).get();
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody LdapUser ldapUser) {
        return Try.of(() -> {
            ldapService.create(ldapUser.getUsername(), ldapUser.getPassword());
            return ResponseEntity.ok("User created successfully");
        }).onFailure(throwable -> {
            Match(throwable.getClass().getName()).of(
                    Case($("javax.naming.NameAlreadyBoundException"), ResponseEntity.ok("Name already exists")),
                    Case($(), ResponseEntity.ok(String.format("Failed to create user. Cause: %s", throwable.getMessage())))
            );
        }).get();
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<String> search(@PathVariable("username") String username) {
        return Try.of(() -> {
            List<LdapUser> searchResult = ldapService.search(username);
            if (!searchResult.isEmpty()) {
                return ResponseEntity.ok(String.format("username: %s found.", username));
            } else {
                return ResponseEntity.ok(String.format("username: %s NOT found.", username));
            }
        }).getOrElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
