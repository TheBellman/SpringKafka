package net.parttimepolymath.spring.springkafka.configuration;

import net.parttimepolymath.spring.springkafka.DataStreamProvider;
import net.parttimepolymath.spring.springkafka.KeyGenerator;
import net.parttimepolymath.spring.springkafka.StringStreamProvider;
import net.parttimepolymath.spring.springkafka.UUIDKeyGenerator;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * holder for general beans we want to pop into existence.
 *
 * @since 2022-12-11
 * @author Robert Hook
 */
@Configuration
public class Config {
    @Value("${default.count}")
    private long defaultCount;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    @Bean
    public RuntimeConfig runtimeConfig() {
        return new RuntimeConfig();
    }

    @Bean
    public CLIParser cliParser(final @Autowired RuntimeConfig config) {
        return new CLIParser(config, defaultCount);
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> producerTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public DataStreamProvider<String> streamProvider() {
        return new StringStreamProvider();
    }

    @Bean
    public KeyGenerator<String> keyGenerator() {
        return new UUIDKeyGenerator();
    }
}
