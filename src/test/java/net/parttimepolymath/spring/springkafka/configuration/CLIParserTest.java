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
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer",
                "--bootstrap-server=localhost:9092"));
        assertTrue(result);
        assertEquals(RuntimeConfig.Mode.PRODUCER, config.getMode());
        assertEquals("localhost:9092", config.getBootstraps().get(0));
        assertEquals(DEFAULT_COUNT, config.getCount());
    }

    @Test
    void fullParse() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer",
                "--bootstrap-server=localhost:9092", "--count=10000"));
        assertTrue(result);
        assertEquals(RuntimeConfig.Mode.PRODUCER, config.getMode());
        assertEquals("localhost:9092", config.getBootstraps().get(0));
        assertEquals(10000, config.getCount());
    }

    @Test
    void parseConsumer() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--consumer",
                "--bootstrap-server=localhost:9092"));
        assertTrue(result);
        assertEquals(RuntimeConfig.Mode.CONSUMER, config.getMode());
        assertEquals("localhost:9092", config.getBootstraps().get(0));
    }

    @Test
    void parseMinimal() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--bootstrap-server=localhost:9092"));
        assertFalse(result);
    }

    @Test
    void parseBootstraps() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--consumer",
                "--bootstrap-server=localhost:9092"));
        assertTrue(result);

        result = instance.parseArgs(new DefaultApplicationArguments("--consumer",
                "--bootstrap-server=localhost:9092","--bootstrap-server=localhost:8181"));
        assertTrue(result);

        result = instance.parseArgs(new DefaultApplicationArguments("--consumer",
                "--bootstrap-server=localhost:9092","--bootstrap-server=localhost"));
        assertFalse(result);

        result = instance.parseArgs(new DefaultApplicationArguments("--consumer",
                "--bootstrap-server=localhost:9092","--bootstrap-server=localhost:jill"));
        assertFalse(result);
    }

    @Test
    void parseBootstrapsBad() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--consumer", "--bootstrap-server"));
        assertFalse(result);

        result = instance.parseArgs(new DefaultApplicationArguments("--consumer", "--bootstrap-server=mary"));
        assertFalse(result);
    }

    @Test
    void parseCount() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer", "--count=500",
                "--bootstrap-server=localhost:9092"));
        assertTrue(result);
        assertEquals(500, config.getCount());
    }

    @Test
    void parseCountNoCount() {
         boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer", "--count",
                 "--bootstrap-server=localhost:9092"));
        assertFalse(result);
    }

    @Test
    void parseCountBadCount() {
        boolean result = instance.parseArgs(new DefaultApplicationArguments("--producer", "--count=foo",
                "--bootstrap-server=localhost:9092"));
        assertFalse(result);
    }

}