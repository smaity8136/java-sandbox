This spring cloud module will illustrate how we the spring cloud consumer groups work. This module simply consumes messages off the Kafka topic
named "numberSequenceChannel" who's messages are published by the springcloud-stream-source-numbersequence module.

This illustration requires 2 instances of this microservice to be running, and will demonstrate that given a consumer group name, each instance will only
consume 1 message from the channel, ensuring that messages are not consumed in duplicate.

I'm still trying to figure out how the partitioning function works in spring cloud, but I configured the following config in coordination with the springcloud-stream-source-numbersequence module:

spring:
  cloud:
    stream:
      bindings:
        numberSequenceChannel:
          group: consumerGroup

        input:
          consumer:
            partitioned: true

      instanceCount: 2

Execute the following below to start 2 seperate instances of this microservice:

gradle bootRun -Dspring.cloud.stream.bindings.input.consumer.partitioned=true -Dspring.cloud.stream.instanceCount=2 -Dspring.cloud.stream.instanceIndex=0 -Dserver.port=8081
gradle bootRun -Dspring.cloud.stream.bindings.input.consumer.partitioned=true -Dspring.cloud.stream.instanceCount=2 -Dspring.cloud.stream.instanceIndex=1 -Dserver.port=8082