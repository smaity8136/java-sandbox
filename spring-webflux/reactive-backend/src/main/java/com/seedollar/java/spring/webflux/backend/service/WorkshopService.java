package com.seedollar.java.spring.webflux.backend.service;

import reactor.core.publisher.Mono;

import com.seedollar.java.spring.webflux.backend.domain.Workshop;

public interface WorkshopService {

  Mono<Workshop> getWorkshop(long workshopId);

}
