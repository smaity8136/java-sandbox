package com.seedollar.java.sandbox.http.caching.controller;

import com.seedollar.java.sandbox.http.caching.model.User;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UserController {

    private static Map<String, User> users = new HashMap<>();

    @GetMapping("/{uid}/cache")
    public ResponseEntity<User> getUserCache(@PathVariable("uid") String uid) {
        User targetUser = users.get(uid);

        if (targetUser != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(targetUser);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{uid}/nocache")
    public ResponseEntity<User> getUserNoCache(@PathVariable("uid") String uid) {
        User user = new User();
        user.setUid(uid);
        user.setBirthDate(LocalDateTime.now());
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore()).body(user);

    }

    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(users.values());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        users.put(user.getUid(), user);
        return ResponseEntity.ok("new user added successfully");
    }
}
