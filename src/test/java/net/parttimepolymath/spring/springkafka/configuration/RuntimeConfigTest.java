package net.parttimepolymath.spring.springkafka.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuntimeConfigTest {

    private RuntimeConfig instance;

    @BeforeEach
    void setUp() {
        instance = new RuntimeConfig();
    }

    @Test
    void getMode() {
        assertNull(instance.getMode());
        instance.setMode(RuntimeConfig.Mode.CONSUMER);
        assertEquals(RuntimeConfig.Mode.CONSUMER, instance.getMode());
    }

    @Test
    void setMode() {
        instance.setMode(RuntimeConfig.Mode.CONSUMER);
        assertEquals(RuntimeConfig.Mode.CONSUMER, instance.getMode());
        instance.setMode(RuntimeConfig.Mode.PRODUCER);
        assertEquals(RuntimeConfig.Mode.PRODUCER, instance.getMode());
    }
}