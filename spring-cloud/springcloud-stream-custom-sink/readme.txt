This module will illustrate how we can create our own spring cloud custom "Sink" which will consume messages from a Kafka topic.

The MYUUIDConsumerSink.java class defines our custom sink and defines the channel name to be "uuidChannel".

The MyGeneratorConsumer.java class will utilize and bind the MyUUIDConsumerSink to a Kafka topic and simple print the UUID to console.

You can start the microservice by executing the following command: gradle bootRun.

Port offset to 8081 so that we can start the springcloud-steam-custom-source microservice to generate the UUID mesasages to be consumed for the example.
