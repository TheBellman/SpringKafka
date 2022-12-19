package net.parttimepolymath.spring.springkafka.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.DefaultApplicationArguments;

import static org.junit.jupiter.api.Assertions.*;

class CLIParserTest {

    public static final int DEFAULT_COUNT = 1000;
    private CLIParser instance;
    private RuntimeConfig config;

    @BeforeEach
    void setUp() {
        config = new RuntimeConfig();
        instance = new CLIParser(config, DEFAULT_COUNT);
    }

    @Test
    void basicParse() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer"));
        assertTrue(result);
        assertEquals(RuntimeConfig.Mode.PRODUCER, config.getMode());
        assertEquals(DEFAULT_COUNT, config.getCount());
    }

    @Test
    void fullParse() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer", "--count=10000"));
        assertTrue(result);
        assertEquals(RuntimeConfig.Mode.PRODUCER, config.getMode());
        assertEquals(10000, config.getCount());
    }

    @Test
    void parseConsumer() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--consumer"));
        assertTrue(result);
        assertEquals(RuntimeConfig.Mode.CONSUMER, config.getMode());
    }

    @Test
    void parseCount() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer", "--count=500"));
        assertTrue(result);
        assertEquals(500, config.getCount());
    }

    @Test
    void parseCountNoCount() {
         boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer", "--count"));
        assertFalse(result);
    }

    @Test
    void parseCountBadCount() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer", "--count=foo"));
        assertFalse(result);
    }

}