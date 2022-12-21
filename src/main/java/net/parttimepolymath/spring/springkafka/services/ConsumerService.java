package net.parttimepolymath.spring.springkafka.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * implementation of the consumer that just logs the key and payload to the console.
 *
 * @author Robert Hook
 * @since 2022-12-20
 */
@Service
@Slf4j
public class ConsumerService<K, V> {
    @Value("${listener.id}")
    private String listenerId;
    private final KafkaListenerEndpointRegistry registry;

    public ConsumerService(@Autowired final KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    public void start() {
        Objects.requireNonNull(registry.getListenerContainer(listenerId)).start();
        log.info("consumer started");
    }

    @KafkaListener(id = "${listener.id}", topics = "${topic.name}", autoStartup = "false", concurrency = "${listen.concurrency:3}")
    public void listen(ConsumerRecord<K, V> record) {
        log.info("consumed {} = {}", record.key(), record.value());
    }
}
