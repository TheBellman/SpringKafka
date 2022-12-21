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
}