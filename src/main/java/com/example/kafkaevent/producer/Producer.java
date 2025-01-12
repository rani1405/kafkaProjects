package com.example.kafkaevent.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void publishMessage() {
        kafkaTemplate.send("topicReplica", "Hello");

        log.info("Message sent successfully!!");
    }
}
