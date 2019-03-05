package com.seedollar.java.sandbox.resilience4j.circuitbreaker;

import com.seedollar.java.sandbox.resilience4j.config.CacheConfiguration;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;
import io.github.resilience4j.circuitbreaker.autoconfigure.CircuitBreakerAutoConfiguration;
import io.github.resilience4j.ratelimiter.autoconfigure.RateLimiterAutoConfiguration;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.IntStream;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude= {CircuitBreakerAutoConfiguration.class, RateLimiterAutoConfiguration.class})
@Slf4j
public class CircuitBreakerTest {

    private CircuitBreakerConfig customCircuitBreakerConfig;

    private CircuitBreaker testCircuitBreaker;

    @Before
    public void init() {
        log.info("initializing test");
        customCircuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(10.0f) // failure rate percentage, 10%
                // the number of calls that need to be evaluated to calculate the failure rate. For example, if the buffer is 10, then 10 calls need to be evaluated before
                // the failure rate can be calculated. So if 1 call out of 10 calls in the buffer have failed, the circuitbreaker will transition to OPEN.
                .ringBufferSizeInClosedState(10)
                // the number of calls that need to be evaluated to calculate the failure rate when the circuit breaker is in HALF-OPEN state. Meaning that 8 calls need to be
                // recorded and the failure rate not at 50% or above before the circuitbreaker transitions from HALF-OPEN to CLOSED.
                .ringBufferSizeInHalfOpenState(5)
                .waitDurationInOpenState(Duration.ofSeconds(5)) // Defines how long the circuit breaker will remain OPEN before transitioning to HALF-OPEN state.
                .build();

        this.testCircuitBreaker = CircuitBreaker.of("testCircuitBreaker", customCircuitBreakerConfig);
        testCircuitBreaker.getEventPublisher()
                .onSuccess(evt -> System.out.println(evt.getEventType().name()))
                .onError(evt -> System.out.println(String.format("%s - %s", evt.getEventType().name(), evt.getThrowable())))
                .onReset(evt -> System.out.println(evt.getEventType().name()))
                .onStateTransition(evt -> System.out.println(String.format("%s - From %s To %s", evt.getEventType().name(), evt.getStateTransition().getFromState().name(), evt.getStateTransition().getToState().name())))
                .onEvent(evt -> System.out.println("Event Fired = " + evt.getEventType().name()));
        this.testCircuitBreaker.reset();
    }

    @Test
    public void testCircuitBreaker_OPEN() {
        log.info("Testing testCircuitBreaker_OPEN");
        // Simulate 9 successful events
        IntStream.range(0, 9).forEach(num -> testCircuitBreaker.onSuccess(0));
        Assert.assertEquals(CircuitBreaker.State.CLOSED, testCircuitBreaker.getState());
        // Simulate 1 failure, which should trigger the circuitbreaker to OPEN
        testCircuitBreaker.onError(0, new RuntimeException("mock failure1"));
        Assert.assertEquals(CircuitBreaker.State.OPEN, testCircuitBreaker.getState());
    }

    @Test
    public void testCircuitBreaker_HALF_OPEN() throws InterruptedException {
        testCircuitBreaker.getEventPublisher()
                .onStateTransition(evt -> System.out.println(String.format("%s - From %s To %s", evt.getEventType().name(), evt.getStateTransition().getFromState().name(), evt.getStateTransition().getToState().name())));
        CheckedFunction0<String> callExecution = CircuitBreaker.decorateCheckedSupplier(testCircuitBreaker, () -> "Success");
        // Simulate 9 successful events
        IntStream.range(0, 9).forEach(num -> testCircuitBreaker.onSuccess(0));
        System.out.println("testCircuitBreaker = " + testCircuitBreaker.getState().name());
        Assert.assertEquals(CircuitBreaker.State.CLOSED, testCircuitBreaker.getState());
        // Simulate 1 failure, which should trigger the circuitbreaker to OPEN
        testCircuitBreaker.onError(0, new RuntimeException("mock failure1"));
        System.out.println("testCircuitBreaker = " + testCircuitBreaker.getState().name());
        Assert.assertEquals(CircuitBreaker.State.OPEN, testCircuitBreaker.getState());
        // Confirm that the circuit breaker is open, and that a CircuitBreakerOpenException is thrown.
        Try<String> invocationTry = Try.of(callExecution);
        Assert.assertTrue(invocationTry.isFailure());
        Assert.assertTrue(invocationTry.getCause() instanceof CircuitBreakerOpenException);
        // Wait 10 seconds, allowing the 8 seconds of delay to transition from OPEN to HALF-OPEN.
        Thread.sleep(10000);
        // Simulate circuitbreaker interaction
        Try.of(callExecution).get();
        System.out.println("testCircuitBreaker = " + testCircuitBreaker.getState().name());
        Assert.assertEquals(CircuitBreaker.State.HALF_OPEN, testCircuitBreaker.getState());
    }

    @Test
    public void testCircuitBreaker_RecoverFunction() {
        // Decorate the circuit breaker with a chained function
        CheckedFunction0<String> eventExecution = CircuitBreaker.decorateCheckedSupplier(testCircuitBreaker, () -> {
            throw new RuntimeException("mock failure"); // Simulate failure
        });

        String eventExecutionResult = Try.of(eventExecution).recover(ex -> "Recovery").get();
        Assert.assertEquals(CircuitBreaker.State.CLOSED, testCircuitBreaker.getState()); // Circuit breaker is still in CLOSED state because recover() function was successful.
        Assert.assertEquals("Recovery", eventExecutionResult);
    }

    @Test
    public void testChainTwoCircuitBreakers() {
        // Define another circuit breaker instance
        CircuitBreaker secondCircuitBreaker = CircuitBreaker.of("secondCircuitBreaker", customCircuitBreakerConfig);

        // Decorate the first circuitbreaker with a function
        CheckedFunction0<String> firstEventExecution = CircuitBreaker.decorateCheckedSupplier(testCircuitBreaker, () -> "First Successful.");

        // Decorate the second circuitbreaker with a function
        CheckedFunction1<String, String> secondEventExecution = CircuitBreaker.decorateCheckedFunction(testCircuitBreaker, firstResult -> firstResult + "Second Successful.");

        String result = Try.of(firstEventExecution).mapTry(secondEventExecution).get();
        Assert.assertEquals("First Successful.Second Successful.", result);
    }

    @Test
    public void testResetCircuitBreaker() throws InterruptedException {
        // Simulate 9 successful events
        IntStream.range(0, 9).forEach(num -> testCircuitBreaker.onSuccess(0));
        testCircuitBreaker.onError(0, new RuntimeException("mock failure"));
        Assert.assertEquals(CircuitBreaker.State.OPEN, testCircuitBreaker.getState());
        testCircuitBreaker.reset();
        Assert.assertEquals(CircuitBreaker.State.CLOSED, testCircuitBreaker.getState());
    }

    @Test
    public void testConditionalFailures() {
        // A given circuit breaker config which will only take IOException instances a valid exceptions to trigger circuitbreaker opening.
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50.0f)
                .ringBufferSizeInClosedState(2)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .recordFailure(throwable -> Match(throwable).of(
                        Case($(instanceOf(IOException.class)), true),
                        Case($(), false)))
                .build();

        CircuitBreaker conditionalFailureCircuitBreaker = CircuitBreaker.of("conditionalFailureCircuitBreaker", circuitBreakerConfig);

        Assert.assertEquals(CircuitBreaker.State.CLOSED, conditionalFailureCircuitBreaker.getState());
        conditionalFailureCircuitBreaker.onSuccess(0);
        // Simulate a failed call, with an invalid exception
        conditionalFailureCircuitBreaker.onError(0, new IllegalArgumentException("invalid exception to trigger circuitbreaker"));
        Assert.assertEquals(CircuitBreaker.State.CLOSED, conditionalFailureCircuitBreaker.getState());
        // Simulate another successful call, so buffer size is fulfilled (2 calls required to calculate failure rate)
        conditionalFailureCircuitBreaker.onSuccess(0);
        // Now simulate a failed call with a valid IOException to trigger circuitbreaker
        conditionalFailureCircuitBreaker.onError(0, new IOException("Valid exception to trigger circuitbreaker"));
        Assert.assertEquals(CircuitBreaker.State.OPEN, conditionalFailureCircuitBreaker.getState());
    }

    @Test
    public void testCircuitBreakerEvents() {
        testCircuitBreaker.onSuccess(0);
        testCircuitBreaker.onError(0, new NoSuchMethodException());
        testCircuitBreaker.reset();
    }

    @Test
    public void testCircuitBreakerMetrics() throws InterruptedException {
        // Decorate circuit breaker with consumer function
        CheckedFunction0<String> fireCall = CircuitBreaker.decorateCheckedSupplier(testCircuitBreaker, () -> "Fire call");
        IntStream.range(0, 9).forEach(num -> testCircuitBreaker.onSuccess(0));
        System.out.println("Current Number of Buffered Calls = " + testCircuitBreaker.getMetrics().getNumberOfBufferedCalls());
        testCircuitBreaker.onSuccess(0);
        System.out.println("Current Number of Buffered Calls = " + testCircuitBreaker.getMetrics().getNumberOfBufferedCalls());
        testCircuitBreaker.onSuccess(0);
        System.out.println("Current Number of Buffered Calls = " + testCircuitBreaker.getMetrics().getNumberOfBufferedCalls());
        testCircuitBreaker.onError(0, new RuntimeException("mock failure"));
        System.out.println("Current Number of Buffered Calls = " + testCircuitBreaker.getMetrics().getNumberOfBufferedCalls());
        System.out.println("Current Number of Success Calls = " + testCircuitBreaker.getMetrics().getNumberOfSuccessfulCalls());
        System.out.println("Current Number of Failed Calls = " + testCircuitBreaker.getMetrics().getNumberOfFailedCalls());
        // These calls should fail because the circuitbreaker is OPEN
        Assert.assertTrue(Try.of(fireCall).isFailure());
        Assert.assertTrue(Try.of(fireCall).isFailure());
        System.out.println("Current Number of Not Permitted Calls = " + testCircuitBreaker.getMetrics().getNumberOfNotPermittedCalls());
        Assert.assertEquals(2, testCircuitBreaker.getMetrics().getNumberOfNotPermittedCalls());
        System.out.println("Current failure rate = " + testCircuitBreaker.getMetrics().getFailureRate());

        // Wait for circuitbreaker to transition to HALF-OPEN
        Thread.sleep(10000);
        // Trigger 5 successful calls
        Assert.assertTrue(Try.of(fireCall).isSuccess());
        Assert.assertTrue(Try.of(fireCall).isSuccess());
        Assert.assertTrue(Try.of(fireCall).isSuccess());
        Assert.assertTrue(Try.of(fireCall).isSuccess());
        Assert.assertEquals(CircuitBreaker.State.HALF_OPEN, testCircuitBreaker.getState());
        Assert.assertTrue(Try.of(fireCall).isSuccess());
        Assert.assertEquals(CircuitBreaker.State.CLOSED, testCircuitBreaker.getState());
    }


}
