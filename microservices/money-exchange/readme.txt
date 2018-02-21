This microservice modules in this example show how to use the following frameworks in collaboration:

- Spring Boot
- Spring Cloud Netflix Ribbon - Client Side Load Balancing
- Spring Cloud Netflix Eureka Naming Server - service registration

There are 3 microservices defined:

1) forex-service: Simple REST API to retrieve a currency pair from a database table
2) currency-conversion-service: Another REST API which makes an invocation to the forex-service in order to perform the currency conversion
3) eureka-naming-service: Microservice bootstrapped with netflix eureka naming server so that the other microservices can register themselves

To illustrate this example do the following:


1) Browse to the eureka-naming-service folder and execute the following command:

gradle bootRun

2) Browse to the forex-service folder and execute the following gradle command:

gradle bootRun -Dserver.port=8000

3) Open another terminal window, browse to the forex-service folder again and execute the following command:

gradle bootRun -Dserver.port=8001

4) Browse to the currency-conversion-service folder and execute:

gradle bootRun

Now we have 4 microservice instances running, 2 forex-service instances on port 8000 and 8001, 1 instance of currency-conversion-service running on port 8100,
and the eureka-naming-service running on port 8761.

To illustrate the load balancing and eureka integration, execute the following requests to see the responses:

http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000
