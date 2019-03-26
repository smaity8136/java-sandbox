package com.seedollar.java.spring.webflux.backend.consumer;

import reactor.core.publisher.Mono;

public interface LicensingConsumer {

    Mono<Boolean> validLicense(String ssn);
}
