package com.tiket.demo.kafka;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan Rahardjo on 4/24/2019
 */
@Component
public class Producer implements IProducer {
    private static final Logger log = LoggerFactory.getLogger(Producer.class.getName());

    private KafkaSender kafkaProducer;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Producer() {
        final Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        final SenderOptions<Integer, String> producerOptions = SenderOptions.create(producerProps);
        kafkaProducer = KafkaSender.create(producerOptions);
    }


    @Override
    public Mono<Void> doLoadData(final SimpleCommand command) {
        final LoadEvent event = new LoadEvent(command.getId(), "SOURCE 1");

        SenderRecord<Integer, String, Integer> message = SenderRecord.create(new ProducerRecord<>("load-data", toBinary(event)), 1);
        return kafkaProducer.send(Mono.just(message)).next();
    }

    private String toBinary(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
