This module is to be used to illustrate how the springcloud-stream-custom-processor module works.

It basically publishes timestamp (Long) value messages to a Kafka channel called "timestampChannel" every 1 second.

To start the microservice, execute: gradle bootRun

This service is offset to port 8080. The springcloud-stream-custom-processor module should be offset to 8081.


