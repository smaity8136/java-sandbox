package com.seedollar.java.spring.webflux.backend.common.util;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.slf4j.MDC;

import reactor.core.publisher.Signal;

import com.seedollar.java.spring.webflux.backend.common.exception.OrchestratorException;

public final class MDCLoggingUtil {

  private static final String CORRELATION_ID = "correlationId";

  private MDCLoggingUtil() {
  }

  public static <T> Consumer<Signal<T>> logOnNext(Consumer<T> logStatement) {
    return signal -> {
      if (!signal.isOnNext()) {
        return;
      }
      invokeLog(signal, logStatement);
    };
  }

  public static <T> Consumer<Signal<T>> logOnError(BiConsumer<T, Throwable> errorLogStatement) {
    return signal -> {
      if (!signal.isOnError()) {
        return;
      }
      invokeError(signal, errorLogStatement);
    };
  }

  private static <T> void invokeLog(Signal<T> signal, Consumer<T> logStatement) {
    Optional<String> correlationId = signal.getContext().getOrEmpty(CORRELATION_ID);

    correlationId.ifPresentOrElse(correlationID -> {
      try (MDC.MDCCloseable closeable = MDC.putCloseable(CORRELATION_ID, correlationID)) {
        logStatement.accept(signal.get());
      }
    }, () -> logStatement.accept(signal.get()));
  }

  private static <T> void invokeError(Signal<T> signal,
      BiConsumer<T, Throwable> errorLogStatement) {
    Optional<String> correlationId = signal.getContext().getOrEmpty(CORRELATION_ID);
    Optional<Throwable> throwable = Optional.ofNullable(signal.getThrowable());

    correlationId.ifPresentOrElse(correlationID -> {
      try (MDC.MDCCloseable closeable = MDC.putCloseable(CORRELATION_ID, correlationID)) {
        errorLogStatement.accept(signal.get(), throwable.map(exception -> {
          if (exception instanceof OrchestratorException) {
            ((OrchestratorException) exception).setCorrelationId(correlationID);
          }
          return exception;
        }).orElse(null));
      }
    }, () -> errorLogStatement.accept(signal.get(), throwable.orElse(null)));
  }

}
