package com.seedollar.java.spring.webflux.backend.common.util;

import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.MDC;

import reactor.core.publisher.Signal;

public final class MDCLoggingUtil {

  public static <T> Consumer<Signal<T>> logOnNext(Consumer<T> logStatement) {
    return signal -> {
      if (!signal.isOnNext()) {
        return;
      }
      Optional<String> correlationId = signal.getContext().getOrEmpty("correlationId");

      correlationId.ifPresentOrElse(apiID -> {
        try (MDC.MDCCloseable closeable = MDC.putCloseable("correlationId", apiID)) {
          logStatement.accept(signal.get());
        }
      }, () -> logStatement.accept(signal.get()));
    };
  }

}
