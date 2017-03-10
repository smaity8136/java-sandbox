package com.seedollar.spring.graphite.controller;

import com.seedollar.spring.graphite.service.HitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by seedollar on 3/10/17.
 */
@RestController
public class HitController {

    private HitService hitService;

    public HitController(HitService hitService) {
        this.hitService = hitService;
    }

    @PostMapping("/hit")
    public ResponseEntity<String> hit() {
        hitService.whack();
        return ResponseEntity.ok("hit confirmed" + LocalDateTime.now());
    }

    @PostMapping("/recover")
    public ResponseEntity<String> recover() {
        hitService.recover();
        return ResponseEntity.ok("recovery confirmed" + LocalDateTime.now());
    }
}
