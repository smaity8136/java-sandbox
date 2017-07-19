This module will illustrate how we can map conditions on the @StreamListener annotation to multiple method consumers.

The module uses RabbitMQ, so a temporary, exclusive queue will automatically be created which is bounded to the 'colorChannel' exchange, will receive messages every 2 seconds and will then be consumed by our @StreamListener annotated methods with different conditions.

The use-case will process colours (String values) and based on certain colours we map them to different method invocations with the @StreamListener annotation.

This module depends on the 'springcloud-stream-source-color' module to publish color messages to the 'colorChannel' exchange.

This module is explicitly offset to port 8081

To start this microservice, execute: gradle bootRun

