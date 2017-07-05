This module will give an illusration of how to configure a error channel destination should a output binding throw an exception. This would mean that the error channel destination will receive an error to be
processed. 

We will name the error channel = 'problemChannel'

This microservice will attempt to publish random numbers to a output channel called 'oddNumberChannel' which only accepts odd numbers. If an even number is generated, we throw an exception so that we can 
confirm that the error goes to the 'problemChannel'.

To start this microservice, execute the following: gradle bootRun

To illustrate the consumption of the odd numbers, start a kafka console consumer:

./kafka-console-consumer.sh --zookeeper localhost:2181 --topic oddNumberChannel

To illustrate that even numbers generate an error and error messages are sent to the problem channel:

./kafka-console-consumer.sh --zookeeper localhost:2181 --topic problemChannel
