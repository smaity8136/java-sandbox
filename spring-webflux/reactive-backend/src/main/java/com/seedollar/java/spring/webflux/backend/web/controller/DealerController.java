package com.seedollar.java.spring.webflux.backend.web.controller;

import com.seedollar.java.spring.webflux.backend.domain.OTP;
import com.seedollar.java.spring.webflux.backend.orchestrator.DealerOrchestrator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@Slf4j
public class DealerController {

    private DealerOrchestrator dealerOrchestrator;

    public DealerController(DealerOrchestrator dealerOrchestrator) {
        this.dealerOrchestrator = dealerOrchestrator;
    }

    @GetMapping("/dealers/generateOTP/{carId}")
    public OTP generateOTP(@PathVariable("carId") long carId) {
        Instant timer = Instant.now();
        OTP otp = dealerOrchestrator.generateOTP(carId).block();
        logTime(timer);
        return otp;
    }

    private static void logTime(Instant start) {
        log.debug("Elapsed time: {}ms", Duration.between(start, Instant.now()).toMillis());
    }
}
