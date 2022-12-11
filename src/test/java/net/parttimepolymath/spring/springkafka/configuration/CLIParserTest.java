package net.parttimepolymath.spring.springkafka.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import static org.junit.jupiter.api.Assertions.*;

class CLIParserTest {

    private CLIParser instance;
    private RuntimeConfig config;

    @BeforeEach
    void setUp() {
        config = new RuntimeConfig();
        instance = new CLIParser(config);
    }

    @Test
    void basicParse() {
        ApplicationArguments args = new DefaultApplicationArguments("--producer", "--bootstrap-server=localhost:9092");
        boolean result = instance.parseArgs(args);
        assertTrue(result);
        assertEquals(RuntimeConfig.Mode.PRODUCER, config.getMode());
        assertEquals("localhost:9092", config.getBootstraps().get(0));
    }

    @Test
    void parseConsumer() {
        ApplicationArguments args = new DefaultApplicationArguments("--consumer", "--bootstrap-server=localhost:9092");
        boolean result = instance.parseArgs(args);
        assertTrue(result);
        assertEquals(RuntimeConfig.Mode.CONSUMER, config.getMode());
        assertEquals("localhost:9092", config.getBootstraps().get(0));
    }

    @Test
    void parseMinimal() {
        ApplicationArguments args = new DefaultApplicationArguments("--bootstrap-server=localhost:9092");
        boolean result = instance.parseArgs(args);
        assertFalse(result);
    }

}