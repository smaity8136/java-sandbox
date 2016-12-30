The SpringKafkaConsumerMain.java class will startup a Kafka consumer factory using the @KafkaListener on the TopicListener class to consume messages, and countdown a latch for every message consumed.

You can simulate producers for the topic using the following command:

./kafka-console-producer.sh --broker-list localhost:9092 --topic test
