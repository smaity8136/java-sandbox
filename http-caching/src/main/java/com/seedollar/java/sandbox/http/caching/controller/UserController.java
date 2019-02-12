package com.seedollar.java.sandbox.http.caching.controller;

import com.seedollar.java.sandbox.http.caching.model.User;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{uid}")
    public ResponseEntity<User> getUser(@PathParam("uid") String uid) {
        User user = new User();
        user.setUid(uid);
        user.setBirthDate(LocalDateTime.now());

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(user);

    }

    @PostMapping
    public ResponseEntity<String> addUser(User user) {
        return ResponseEntity.ok("new user added successfully");
    }
}
