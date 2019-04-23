package com.seedollar.java.spring.webflux.backend.common.util;


import io.vavr.control.Try;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seedollar.java.spring.webflux.backend.common.constant.OrchestratorConstants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MDCLoggingUtilTest {

  private static final Logger logger = LoggerFactory.getLogger(MDCLoggingUtilTest.class);

  private final String MOCK_CORRELATION_ID = "d8f34946-3363-443d-8e89-7790402d9ce0";

  @BeforeEach
  void setup() {
    TestLoggingAppender.clearLogs();
  }

  @Test
  @DisplayName("Test that the logOnNext() method is propagating the correlationID defined by a Mono")
  void testLogOnNext() {
    Mono.just("Test Mono")
        .doOnEach(
            MDCLoggingUtil.logOnNext(obj -> logger.info("A test logging message")))
        .subscriberContext(
            context -> context.put(OrchestratorConstants.CORRELATION_ID, MOCK_CORRELATION_ID))
        .block();

    Assertions.assertEquals(1, TestLoggingAppender.getLogs().size());
    LogEvent logEvent = TestLoggingAppender.getLogs().get(0);
    Assertions
        .assertTrue(logEvent.getContextData().containsKey(OrchestratorConstants.CORRELATION_ID));
    Assertions.assertEquals(MOCK_CORRELATION_ID,
        logEvent.getContextData().getValue(OrchestratorConstants.CORRELATION_ID));
  }

  @Test
  @DisplayName("Test that the logOnNext() method still prints a log statement even when the correlation ID is NOT populated in the reactive context")
  void testLogOnNext_MissingCorrelationId() {
    Mono.just("Test Mono")
        .doOnEach(
            MDCLoggingUtil.logOnNext(obj -> logger.info("A test logging message")))
        .block();

    Assertions.assertEquals(1, TestLoggingAppender.getLogs().size());
    LogEvent logEvent = TestLoggingAppender.getLogs().get(0);
    Assertions
        .assertFalse(logEvent.getContextData().containsKey(OrchestratorConstants.CORRELATION_ID));
  }

  @Test
  @DisplayName("Test that the logOnError() method is propagating the correlationID defined by a Mono")
  void testLogOnError() {
    Try.of(() -> Flux.range(1, 5).map(i -> {
      if (i == 3) {
        throw new RuntimeException("mock exception");
      }
      return i;
    })
        .doOnEach(
            MDCLoggingUtil
                .logOnError((obj, throwable) -> logger.error("A test error message", throwable)))
        .doOnEach(
            MDCLoggingUtil
                .logOnNext(obj -> logger.info("Logging {}", obj)))
        .subscriberContext(
            context -> context.put(OrchestratorConstants.CORRELATION_ID, MOCK_CORRELATION_ID))
        .blockLast()).onFailure(throwable -> {

    }).onFailure(throwable -> {
      Assertions.assertEquals(3, TestLoggingAppender.getLogs().size());
      LogEvent logEvent = TestLoggingAppender.getLogs().get(2);
      Assertions.assertEquals(Level.ERROR, logEvent.getLevel());
      Assertions
          .assertTrue(logEvent.getContextData().containsKey(OrchestratorConstants.CORRELATION_ID));
      Assertions.assertEquals(MOCK_CORRELATION_ID,
          logEvent.getContextData().getValue(OrchestratorConstants.CORRELATION_ID));
    });
  }

  @Test
  @DisplayName("Test that the logOnError() method still prints a error log statement even when the correlation ID is NOT populated in the reactive context")
  void testLogOnError_MissingCorrelationId() {
    Try.of(() -> Mono.just("test mono").map(s -> {
      throw new RuntimeException("mock exception");
    })
        .doOnEach(
            MDCLoggingUtil
                .logOnError((obj, throwable) -> logger.error("A test error message", throwable)))
        .block()).onFailure(throwable -> {

    }).onFailure(throwable -> {
      Assertions.assertTrue(TestLoggingAppender.getLogs().size() == 1);
      LogEvent logEvent = TestLoggingAppender.getLogs().get(0);
      Assertions.assertEquals(Level.ERROR, logEvent.getLevel());
      Assertions
          .assertFalse(logEvent.getContextData().containsKey(OrchestratorConstants.CORRELATION_ID));
    });
  }
}
