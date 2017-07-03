This example shows how you can create your own Custom Spring Cloud Stream "Source" which will publish messages to a channel.

The MyUUIDGeneratorSource.java class defines your Source, and simply defines an @Output with a the name of the channel.

The UUIDGenerator.java is the publisher which will utilize and enable the MyUUIDGeneratorSource and defines the binding using the @InboundChannelAdapter annotation to the channel name.

This module will therefore publish messages to a Kafka topic called "uuidChannel" every 1 second.

You can start the microservice by executing the following command: gradle bootRun
