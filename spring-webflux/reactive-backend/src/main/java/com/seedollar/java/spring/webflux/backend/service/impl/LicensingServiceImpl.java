package com.seedollar.java.spring.webflux.backend.service.impl;

import com.seedollar.java.spring.webflux.backend.consumer.LicensingConsumer;
import com.seedollar.java.spring.webflux.backend.service.LicensingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LicensingServiceImpl implements LicensingService {

    private LicensingConsumer licensingConsumer;

    public LicensingServiceImpl(LicensingConsumer licensingConsumer) {
        this.licensingConsumer = licensingConsumer;
    }

    @Override
    public Mono<Boolean> driverHasValidLicense(String ssn) {
        return licensingConsumer.validLicense(ssn);
    }
}
