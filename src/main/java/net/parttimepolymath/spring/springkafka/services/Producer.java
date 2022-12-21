package net.parttimepolymath.spring.springkafka.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Producer used to write to Kafka
 *
 * @author Robert Hook
 * @since 2022-12-20
 */
@Service
@Slf4j
public class Producer<K, V> {
    @Value("${topic.name}")
    private String targetTopic;
    private final AtomicLong successCounter = new AtomicLong();
    private final AtomicLong errorCounter = new AtomicLong();
    private final KeyGenerator<K> keyGenerator;
    private final DataStreamProvider<V> dataStreamProvider;

    // without building our own, Spring Boot will automagically build one for us
    private final KafkaTemplate<K, V> producerTemplate;

    public Producer(@Autowired final KeyGenerator<K> keyGenerator,
                    @Autowired final DataStreamProvider<V> dataStreamProvider,
                    @Autowired KafkaTemplate<K, V> producerTemplate) {
        this.keyGenerator = keyGenerator;
        this.dataStreamProvider = dataStreamProvider;
        this.producerTemplate = producerTemplate;
    }

    public void execute(long count) {
        log.info("executing with {}", count);
        successCounter.set(0);
        errorCounter.set(0);
        dataStreamProvider.getDataStream().limit(count).map(body -> new ProducerRecord<>(targetTopic,
                keyGenerator.getKey(), body)).forEach(this::sendToKafka);
        producerTemplate.flush();
        log.info("Success count = {}. error count = {}", successCounter.get(), errorCounter.get());
    }

    private void sendToKafka(final ProducerRecord<K, V> record) {
        CompletableFuture<SendResult<K, V>> future = producerTemplate.send(record);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                successCounter.incrementAndGet();
            } else {
                log.error("Failed to send {} due to {}", record.value().toString(), ex.getMessage());
                errorCounter.incrementAndGet();
            }
        });
    }
}
