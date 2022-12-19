package net.parttimepolymath.spring.springkafka;

import net.parttimepolymath.spring.springkafka.configuration.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VersionConsumerTest {
    private VersionConsumer instance;

    @BeforeEach
    void setUp() {
        Version version = new Version();
        instance = new VersionConsumer(version);
    }

    @Test
    void afterPropertiesSet() throws Exception {
        assertDoesNotThrow(() -> instance.afterPropertiesSet());
    }
}