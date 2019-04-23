package com.seedollar.java.spring.webflux.backend.web.filter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.seedollar.java.spring.webflux.backend.common.exception.OrchestratorException;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Component
public class CorrelationFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange serverWebExchange,
      WebFilterChain webFilterChain) {

    List<String> correlationIdHeader = serverWebExchange.getRequest()
        .getHeaders().get("correlationId");

    return webFilterChain.filter(serverWebExchange).subscriberContext(context -> Context
        .of("correlationId",
            Optional.ofNullable(correlationIdHeader).map(headers -> headers.get(0)).orElse(UUID.randomUUID().toString())));
  }
}

