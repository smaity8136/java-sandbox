This module illustrates how we can marshall Java POJOs to JSON Strings when we publish over RabbitMQ, and also how we can unmarshall the JSON String back to a Java POJO when we consume.

So there is one exchange (test-exchange) which is a Direct Exchange and will have 2 queues bound accordingly:

1) customer-queue (routing-key = customer)
2) sales-representative-queue (routing-key = salesRep)

So we define 2 message handler solutions:

1) A SimpleMessageListenerContainer which listens on the customer-queue and also specifies the MessageListener, which is a MessageListenerAdapter with a specified MessageConverter = Jackson2JsonMessageConverter. This ensures that
when the mesage is consumed, the JSON payload is converted back to a Customer.java object.

2) A message handler defined by the @RabbitListener annotation on the SalesRepresentativeMessageListener.java class. The handler requires a RabbitListenerContainerFactory instance which also explicitly sets the message converter = Jackson2JsonMessageConverter,
ensuring that the JSON string payload is converted to a SalesRepresentative java object.

To run the example, execute the following: gradle clean test (We only have a test for the illustation)
