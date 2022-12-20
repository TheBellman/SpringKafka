package net.parttimepolymath.spring.springkafka.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KafkaConfigTest {
    private KafkaConfig instance;

    @BeforeEach
    void setUp() {
        instance = new KafkaConfig();
    }


    @Test
    void producerConfigs() {
        assertAll("producerConfigs",
                () -> {
                    assertNotNull(instance.producerConfigs());
                    assertFalse(instance.producerConfigs().isEmpty());
                }
        );
    }

    @Test
    void consumerConfigs() {
        assertAll("consumerConfigs",
                () -> {
                    assertNotNull(instance.consumerConfigs());
                    assertFalse(instance.consumerConfigs().isEmpty());
                }
        );
    }
    // TODO this falls over with some internal failure inside DefaultKafkaConsumerFactory
//    @Test
//    void consumerFactory() {
//        assertNotNull(instance.consumerFactory());
//    }

    // TODO this falls over with some internal failure inside DefaultKafkaConsumerFactory
//    @Test
//    void kafkaListenerContainerFactory() {
//        assertNotNull(instance.kafkaListenerContainerFactory());
//    }

    // TODO this falls over with some internal failure inside DefaultKafkaProducerFactory
//    @Test
//    void producerFactory() {
//        assertNotNull(instance.producerFactory());
//    }

    // TODO this falls over with some internal failure inside DefaultKafkaProducerFactory
//    @Test
//    void producerTemplate() {
//    assertNotNull(instance.producerTemplate());
//    }
}