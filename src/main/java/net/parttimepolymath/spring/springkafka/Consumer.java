package net.parttimepolymath.spring.springkafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * consumer interface used to "springify" injection.
 * @param <K> the type of key in our stream.
 * @param <V> the type of data in our stream
 *
 * @author Robert Hook
 * @since 2022-12-20
 */
public interface Consumer<K, V> {
    void start();
    void listen(ConsumerRecord<K, V> record);
}
