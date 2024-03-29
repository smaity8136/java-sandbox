package com.seedollar.java.spring.webflux.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

import com.seedollar.java.spring.webflux.backend.common.util.MDCLoggingUtil;
import com.seedollar.java.spring.webflux.backend.common.util.Transformer1;
import com.seedollar.java.spring.webflux.backend.consumer.WorkshopConsumer;
import com.seedollar.java.spring.webflux.backend.domain.Workshop;
import com.seedollar.java.spring.webflux.backend.domain.dto.WorkshopResponse;
import com.seedollar.java.spring.webflux.backend.service.WorkshopService;

@Service
@Slf4j
public class WorkshopServiceImpl implements WorkshopService {

  private WorkshopConsumer workshopConsumer;
  private Transformer1<WorkshopResponse, Workshop> workshopTransformer;

  public WorkshopServiceImpl(WorkshopConsumer workshopConsumer, Transformer1<WorkshopResponse, Workshop> workshopTransformer) {
    this.workshopConsumer = workshopConsumer;
    this.workshopTransformer = workshopTransformer;
  }

  @Override
  public Mono<Workshop> getWorkshop(long workshopId) {
    return workshopConsumer.getWorkshop(workshopId).map(workshopTransformer)
        .doOnEach(MDCLoggingUtil.logOnNext(accessory -> log.info("Retrieving workshop for workshopId: {}", workshopId)));
  }
}
