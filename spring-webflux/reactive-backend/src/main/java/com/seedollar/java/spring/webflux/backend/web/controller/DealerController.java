package com.seedollar.java.spring.webflux.backend.web.controller;

import java.time.Duration;
import java.time.Instant;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.seedollar.java.spring.webflux.backend.common.util.MDCLoggingUtil;
import com.seedollar.java.spring.webflux.backend.domain.OTP;
import com.seedollar.java.spring.webflux.backend.orchestrator.DealerOrchestrator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@RestController
@Slf4j
public class DealerController {

  private DealerOrchestrator dealerOrchestrator;

  public DealerController(DealerOrchestrator dealerOrchestrator) {
    this.dealerOrchestrator = dealerOrchestrator;
  }

  @GetMapping("/dealers/generateOTP/{carId}")
  public Mono<OTP> generateOTP(@PathVariable("carId") long carId,
      ServerHttpRequest serverHttpRequest) {
    Instant timer = Instant.now();
    return dealerOrchestrator.generateOTP(carId)
        .doOnEach(MDCLoggingUtil.logOnNext(r -> log
            .info("Dealer received request to generate OTP for carId {}", r.getCar().getCarId())))
        .subscriberContext(context -> enrichCorrelationId(context, serverHttpRequest))
        .doOnTerminate(() -> logTime(timer));
  }

  private static void logTime(Instant start) {
    log.debug("Elapsed time: {}ms", Duration.between(start, Instant.now()).toMillis());
  }

  private static Context enrichCorrelationId(Context reactiveContext, ServerHttpRequest serverHttpRequest) {
    return reactiveContext.put("correlationId", serverHttpRequest.getHeaders().get("correlationId").get(0));
  }
}
