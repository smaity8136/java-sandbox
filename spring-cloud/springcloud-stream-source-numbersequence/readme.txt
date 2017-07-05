This spring cloud module will assist with publishing a sequence of numbers to a Kafka topic for us to consume and illustrate how consumer groups work
in Spring Cloud. Thus the following module should be used in conjuction with this one:

- springcloud-stream-consumer-groups

Start this microservice by executing: gradle bootRun

This microservice should be offset to http port 8080 by default.

I still trying to figure out how the partitioning function works in spring cloud stream. We thus configure this output binding with the following configuration:

spring.cloud.stream.bindings.output.producer.partitionCount=2
spring.cloud.stream.bindings.output.producer.partitionKeyExtractorClass=za.co.seedollar.sandbox.springcloud.strategy.PartitionKeyExtractor


