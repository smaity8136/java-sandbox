package com.seedollar.java.spring.webflux.backend.consumer;

import reactor.core.publisher.Mono;

import com.seedollar.java.spring.webflux.backend.domain.dto.WorkshopResponse;

public interface WorkshopConsumer {

  Mono<WorkshopResponse> getWorkshop(long workshopId);

}
