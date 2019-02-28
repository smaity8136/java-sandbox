Resilience4J
============

# CircuitBreaker

Check the `CircuitBreakerTest.java` for more details.

There are a number of ways to implement a CircuitBreaker:

- All java configuration. See `CircuitBreakerConfiguration`
- `application.yml` configuration and `@CircuitBreaker` annotation. _NOTE: In conjunction with **resilience4j-spring-boot2** module, only circuitbreakers and ratelimters defined in the application.yml file will correctly integrate with the health actuator endpoint at: `http://localhost:8080/actuator/health` or `http://localhost:8080/actuator/circuitbreakers` or `http://localhost:8080/actuator/circuitbreaker-events`_

The example defines 3 circuit breaker instances:

### createAccount

You can test the circuitbreaker by invoking the following curl commands to simulate failures and successes:

To simulate a successful call: 

`curl -X POST 'http://localhost:8080/accounts/create/true'`

To simulate a failed call: 

`curl -X POST 'http://localhost:8080/accounts/create/false'`

To monitor the circuitbreaker:

`http://localhost:8080/actuator/health`

### updateAccount

You can test the circuitbreaker by invoking the following curl commands to simulate failures and successes:

To simulate a successful call: 

`curl -X PUT 'http://localhost:8080/accounts/update/true'`

To simulate a failed call: 

`curl -X PUT 'http://localhost:8080/accounts/update/false'`

To monitor the circuitbreaker:

`http://localhost:8080/actuator/health`

### deleteAccount

You can test the circuitbreaker by invoking the following curl commands to simulate failures and successes:

To simulate a successful call: 

`curl -X DELETE 'http://localhost:8080/accounts/pass'`

To simulate a failed call: 

`curl -X DELETE 'http://localhost:8080/accounts/fail'`

The deleteAccount circuit breaker is not registered with the spring boot actuator, so you will have to monitor
the logs to see the circuit breaker transtions.

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

#Cache

To confirm a cache hit or miss, execute the following curl command:

`curl -X GET 'http://localhost:8080/branches/Carlton'`

On first invocation, you should see that there is a **CACHE_MISS** event, and a log to indicating that a new branch is being created:

```
...
2019-02-27 13:18:08.871  INFO 207671 --- [nio-8080-exec-2] c.s.j.s.r.config.CacheConfiguration      : Cache Miss - Event = CACHE_MISS, CacheKey = Carlton
2019-02-27 13:18:08.871  INFO 207671 --- [nio-8080-exec-2] c.s.j.s.r.s.impl.BranchServiceImpl       : Building new branch with name: Carlton
```

On second invocation of the same curl command, you will see there is a **CACHE_HIT** event, and no log of a new branch being created:

`curl -X GET 'http://localhost:8080/branches/Carlton'`

```
...
2019-02-27 13:18:08.871  INFO 207671 --- [nio-8080-exec-2] c.s.j.s.r.config.CacheConfiguration      : Cache Miss - Event = CACHE_MISS, CacheKey = Carlton
2019-02-27 13:18:08.871  INFO 207671 --- [nio-8080-exec-2] c.s.j.s.r.s.impl.BranchServiceImpl       : Building new branch with name: Carlton
2019-02-27 13:18:56.836  INFO 207671 --- [nio-8080-exec-3] c.s.j.s.r.config.CacheConfiguration      : Cache Hit - Event = CACHE_HIT, CacheKey = Carlton
```

# TimeLimiter

Given a `MainframeService.calculateBookValue()` method which takes 5 seconds to complete, and a timeLimiter with a timeout of 2 seconds, execute the following curl command:

`curl -X GET 'http://localhost:8080/mainframe/bookValue'`

You can confirm that the timeLimiter kicks in after 2 seconds and interrupts the calculation thread:

```
2019-02-27 14:03:18.639  INFO 215225 --- [pool-1-thread-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : calculate book value process interrupted.
2019-02-27 14:03:18.641  INFO 215225 --- [nio-8080-exec-1] c.s.j.s.r.s.impl.MainframeServiceImpl    : Calculating book value timed out, returning 9999 default.
```
