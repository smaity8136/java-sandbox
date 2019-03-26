package com.seedollar.java.spring.webflux.backend.service;

import reactor.core.publisher.Mono;

public interface LicensingService {

    Mono<Boolean> driverHasValidLicense(String ssn);
}
