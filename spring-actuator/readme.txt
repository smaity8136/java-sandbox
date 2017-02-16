- Make sure you have the following dependencies to enable spring actuator:

spring-boot-starter-actuator
spring-boot-starter-security

- I defined a custom endpoint (CustomActuatorEndpoint) which simply extends the AbstractEndpoint. The endpoint can be accessed using the url mapping: http://localhost:8082/customEndpoint



