package com.seedollar.java.spring.webflux.backend.consumer.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.seedollar.java.spring.webflux.backend.consumer.WorkshopConsumer;
import com.seedollar.java.spring.webflux.backend.domain.dto.WorkshopResponse;

import reactor.core.publisher.Mono;

@Component
public class WorkshopConsumerImpl implements WorkshopConsumer {

  private WebClient workshopWebClient;

  public WorkshopConsumerImpl(WebClient workshopWebClient) {
    this.workshopWebClient = workshopWebClient;
  }

  @Override
  public Mono<WorkshopResponse> getWorkshop(long workshopId) {
    return workshopWebClient.get()
        .uri("/workshops/{workshopId}", workshopId)
        .retrieve().bodyToMono(WorkshopResponse.class);
  }
}
