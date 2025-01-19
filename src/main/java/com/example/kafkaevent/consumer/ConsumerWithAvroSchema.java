package com.example.kafkaevent.consumer;


import com.example.avro.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerWithAvroSchema {


   @KafkaListener(topics = "empTopic1", groupId = "emp-consumer-group-4")
    public void consumeEmployeeRecord(Employee employee) {
       log.info("name:{}, id:{} ", employee.getEmpName(), employee.getId());
        log.info("Record successfully consumed!!");
    }


}
