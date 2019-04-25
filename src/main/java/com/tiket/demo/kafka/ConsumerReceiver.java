package com.tiket.demo.kafka;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Ryan Rahardjo on 4/24/2019
 */
@Component
public class ConsumerReceiver {
    private static final Logger log = LoggerFactory.getLogger(ConsumerReceiver.class.getName());

    private KafkaReceiver kafkaReceiver;

    private Random r = new Random();

    private ObjectMapper objectMapper = new ObjectMapper();

    private IConsumer consumer;

    public ConsumerReceiver(IConsumer consumer) {
        this.consumer = consumer;

        final Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-load-1");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-load");
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        ReceiverOptions<Object, Object> consumerOptions = ReceiverOptions.create(consumerProps)
                .subscription(Collections.singleton("load-data"))
                .addAssignListener(partitions -> log.debug("onPartitionsAssigned {}", partitions))
                .addRevokeListener(partitions -> log.debug("onPartitionsRevoked {}", partitions));

        kafkaReceiver = KafkaReceiver.create(consumerOptions);

        // create consumer
        ((Flux<ReceiverRecord>) kafkaReceiver.receive())
                .doOnNext(r -> {
                    // each event we receive, we convert to a LoadEvent and process it
                    final LoadEvent event = fromBinary((String) r.value(), LoadEvent.class);

                    processEvent(event);

                    r.receiverOffset().acknowledge();
                })
                .subscribe();
    }

    private void processEvent(LoadEvent event) {
        consumer.loadData(event);
    }

    private <T> T fromBinary(String object, Class<T> resultType) {
        try {
            return objectMapper.readValue(object, resultType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
