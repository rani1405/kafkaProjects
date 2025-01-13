package com.example.kafkaevent.producer;


import com.example.avro.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerWithAvroSchema {


    @Autowired
    private KafkaTemplate<Integer, Employee> kafkaTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void publishCustomerRecord() {
        kafkaTemplate.send("empTopic1", 1, new Employee(100, "Rani"));
        log.info("Record published successfully!!");
    }

}
