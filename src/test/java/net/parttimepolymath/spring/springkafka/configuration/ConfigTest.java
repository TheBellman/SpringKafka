package net.parttimepolymath.spring.springkafka.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigTest {

    private Config instance;

    @BeforeEach
    void setUp() {
        instance = new Config();
    }

    @Test
    void runtimeConfig() {
        assertNotNull(instance.runtimeConfig());
    }

    @Test
    void cliParser() {
        RuntimeConfig rc = instance.runtimeConfig();
        assertNotNull(rc);
        assertNotNull(instance.cliParser(rc));
    }

    // TODO this falls over with some internal failure inside DefaultKafkaProducerFactory
//    @Test
//    void producerFactory() {
//        assertNotNull(instance.producerFactory());
//    }

    @Test
    void producerConfigs() {
        assertNotNull(instance.producerConfigs());
        assertFalse(instance.producerConfigs().isEmpty());
    }

    // TODO this falls over with some internal failure inside DefaultKafkaProducerFactory
//    @Test
//    void producerTemplate() {
//    assertNotNull(instance.producerTemplate());
//    }

    @Test
    void streamProvider() {
        assertNotNull(instance.streamProvider());
    }

    @Test
    void keyGenerator() {
        assertNotNull(instance.keyGenerator());
    }
}