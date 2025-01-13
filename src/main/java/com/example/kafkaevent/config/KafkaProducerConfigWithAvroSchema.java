package com.example.kafkaevent.config;


import com.example.avro.Employee;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaProducerConfigWithAvroSchema {


    @Bean
    public ProducerFactory producerFactory() {
        Map producerConfigs = new HashMap<>();


        //Bootstrap server
        producerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");

        //Serialization
        producerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        //Schema registry
        producerConfigs.put("schema.registry.url", "http://localhost:8081");

        ////Acknowledgment
        producerConfigs.put(ProducerConfig.ACKS_CONFIG, "all");

        log.info("producer config properties-- " + producerConfigs.toString());

        return new DefaultKafkaProducerFactory<String, Employee>(producerConfigs);
    }


    @Bean
    public KafkaTemplate<Integer, Employee> kafkaTemplate() {
        return new KafkaTemplate(producerFactory());
    }


}
