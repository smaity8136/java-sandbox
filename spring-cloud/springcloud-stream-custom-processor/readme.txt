This module illustrates how we can create our own custom spring cloud Processor. This module needs the springcloud-stream-source-timestamp module to generate timestamp messages
and publish them to a Kafka topic called "timestampChannel".

This module then consumes the timestamp messages, transforms them to a java.util.Date by parsing the long value and publishing the date representation to a Kafka topic called "timestampTransformedChannel".

You can start the microservice by executing: gradle bootRun

Note this microservice is offet to 8081.

You can check the result of the transformed messages by executing using the Kafka console consumer command:

./kafka-console-consumer.sh --zookeeper localhost:2181 --topic timestampTransformedChannel
