package com.seedollar.java.spring.webflux.backend.service.impl;

import com.seedollar.java.spring.webflux.backend.common.util.MDCLoggingUtil;
import com.seedollar.java.spring.webflux.backend.consumer.LicensingConsumer;
import com.seedollar.java.spring.webflux.backend.service.LicensingService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LicensingServiceImpl implements LicensingService {

    private LicensingConsumer licensingConsumer;

    public LicensingServiceImpl(LicensingConsumer licensingConsumer) {
        this.licensingConsumer = licensingConsumer;
    }

    @Override
    public Mono<Boolean> driverHasValidLicense(String ssn) {
        return licensingConsumer.validLicense(ssn)
            .doOnEach( MDCLoggingUtil
                .logOnNext(result -> log.info("Retrieving license for ssn: {}", ssn)));
    }
}
