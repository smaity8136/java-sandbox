package com.seedollar.java.kafka.producer.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListenerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by seedollar on 12/30/16.
 */
@Configuration
@EnableKafka
public class SpringKafkaProducerConfiguration {

    @Bean
    public ProducerFactory<Integer, String> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<Integer, String>(kafkaProducerConfigs());
    }

    @Bean
    public Map<String, Object> kafkaProducerConfigs() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() {
        KafkaTemplate<Integer, String> kafkaTemplate = new KafkaTemplate<>(kafkaProducerFactory());
        kafkaTemplate.setProducerListener(new ProducerListenerAdapter<Integer, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, Integer key, String value, RecordMetadata recordMetadata) {
                System.out.println(String.format("topic %s sent value = %s", topic, value));
            }

            @Override
            public boolean isInterestedInSuccess() {
                return true; // This must be true if we want the "onSuccess" method to execute
            }
        });



        return kafkaTemplate;
    }
}
