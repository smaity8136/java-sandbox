This module will illustrate how to utilize the @SendTo annotation to simulate a request/reply process for spring cloud stream bindings.

The microservice generates a random UUID string and publishes it to the "cashChannel".

Then we define a Processor (CashDerivativeProcessor) which consumes messages off the "cashChannel" and then processes the UUID string, counting all the
alphabetical characters and then publishes the aggegated total to the "derivativeChannel".

To start this microservice, execute the following: gradle bootRun

You can also start a Kafka Console Consumer to show the messages being published to the "derivativeChannel".
