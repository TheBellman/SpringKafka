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

    @Test
    void streamProvider() {
        assertNotNull(instance.streamProvider());
    }

    @Test
    void keyGenerator() {
        assertNotNull(instance.keyGenerator());
    }
}