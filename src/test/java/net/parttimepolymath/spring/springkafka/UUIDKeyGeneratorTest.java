package net.parttimepolymath.spring.springkafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UUIDKeyGeneratorTest {
    private UUIDKeyGenerator instance;

    @BeforeEach
    void setUp() {
        instance = new UUIDKeyGenerator();
    }

    @Test
    void getKey() {
        assertAll(
                "key",
                () -> {
                    String key = instance.getKey();
                    assertNotNull(key);
                    assertFalse(key.isBlank());
                }

        );
    }
}