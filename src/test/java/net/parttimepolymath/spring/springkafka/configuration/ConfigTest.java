package net.parttimepolymath.spring.springkafka.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}