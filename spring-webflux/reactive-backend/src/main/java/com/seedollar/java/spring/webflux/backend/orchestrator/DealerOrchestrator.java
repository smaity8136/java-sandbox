package com.seedollar.java.spring.webflux.backend.orchestrator;

import com.seedollar.java.spring.webflux.backend.domain.OTP;
import reactor.core.publisher.Mono;

public interface DealerOrchestrator {

    Mono<OTP> generateOTP(long carId);
}
