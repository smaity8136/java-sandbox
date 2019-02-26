Resilience4J
============

# CircuitBreaker

Check the `CircuitBreakerTest.java` for more details.

# RateLimiter

- Start up the spring boot app `gradle bootRun`
- Execute the Apache Batch command to invoke calls to the spring boot REST endpoint:
`ab -n 100 -c 100 -m GET http://localhost:8080/accounts`

You will see the rate limiter kick in intermittently, and only allow 5 calls per second. Any more
than that an exception will be thrown:

`io.github.resilience4j.ratelimiter.RequestNotPermitted: Request not permitted for limiter: getAllAccountsRateLimiter`

## Dynamic RateLimiter change at runtime

- Start up the spring boot app `gradle bootRun`
- Execute the Apache Batch command to invoke calls to the spring boot REST endpoint:
`ab -n 1000 -c 1000 -m GET http://localhost:8080/accounts`

We are using a CountdownLatch which starts at 50, and whenever a GET request is successfully invoked, 
we countdown the latch. When the latch reaches 0, we dynamically update the RateLimiter to process
100 requests/per second.

# Bulkhead


