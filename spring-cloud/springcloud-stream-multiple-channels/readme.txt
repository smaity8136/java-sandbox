This module will give an illustration of how you can have multiple channel bindings in the same Spring Cloud Stream interface. In this example, we define a Judge.java interface which
has 3 bindings:

1) An @Input to a channel called: "sentenceChannel"
2) An @Output to a channel called: "guiltyChannel"
2) An @Output to a channel called: "innocentChannel"

Then we define a JudgeService component which then publishes sentence messages in the form of a UUID string representation. This consumer then does a judgement by parsing the
UUID sting valiating each character in the String representation. If the character is alphabetical, we publish to the "guiltyChannel", else we publish to the "innocentChannel".

You can start the microservice by executing: gradle bootRun

You can then start Kafka console consumers which listen on on both the "guiltyChannel" and "innocentChannel" by executing the following commands in seperate terminals:

./kafka-console-consumer.sh --zookeeper localhost:2181 --topic guiltyChannel

./kafka-console-consumer.sh --zookeeper localhost:2181 --topic innocentChannel
