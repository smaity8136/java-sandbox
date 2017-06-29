This microservice basically outputs a timestamp sting to a Output channel ("timeChannel") every second.

You will need Kafka, and start the following kafka consumer to consume the messages:

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic timeChannel --from-beginning

Then start the microservice by executing: gradle bootRun
