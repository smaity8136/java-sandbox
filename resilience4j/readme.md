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

# Retry

To confirm that a retry policy is working, execute the following curl command:

`curl -X POST 'http://localhost:8080/mainframe/sync/fail' -v`

You can verify that there are 5 log entries confirming that the account sync feature was executed 5 times, with a 2 second delay in between each retry.

```
2019-02-27 11:14:46.733  INFO 187215 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Executing mainframe sync...
2019-02-27 11:14:48.734  INFO 187215 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Executing mainframe sync...
2019-02-27 11:14:50.734  INFO 187215 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Executing mainframe sync...
2019-02-27 11:14:52.735  INFO 187215 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Executing mainframe sync...
2019-02-27 11:14:54.735  INFO 187215 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Executing mainframe sync...
```

To confirm a retryOnResult scenario, execute the following curl command:

`curl -X POST 'http://localhost:8080/mainframe/batch' -v`

You will see on the third retry, we trigger a retry-on-result, which should then return the result `retryOnResultSuccess`:

```
2019-02-27 11:43:49.395  INFO 192642 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Triggering batch jobs...
2019-02-27 11:43:51.397  INFO 192642 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Triggering batch jobs...
2019-02-27 11:43:53.397  INFO 192642 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Triggering batch jobs...
2019-02-27 11:43:53.400  INFO 192642 --- [nio-8080-exec-1] c.s.j.s.r.c.MainframeController          : Batch Jobs Result: retryOnResultSuccess
```