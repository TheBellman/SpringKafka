package net.parttimepolymath.spring.springkafka.configuration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static net.parttimepolymath.spring.springkafka.configuration.Version.UNKNOWN;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Note that we have to explicitly use the EnableConfigurationProperties to trigger the properties passing behaviour,
 * but that this is on by default in the application itself.
 */
@SpringBootTest
class VersionTest {

    @Autowired
    private Version instance;

    @Test
    void testToString() {
        assertEquals(String.format("Version(name=%s, version=%s, build=%s, " + "profile=%s)", instance.getName(),
                instance.getVersion(), instance.getBuild(), instance.getProfile()), instance.toString());
    }

    @Test
    void getName() {
        assertNotNull(instance.getName());
        assertEquals("Spring Kafka Toy", instance.getName());
    }

    @Test
    void getVersion() {
        assertNotNull(instance.getVersion());
        assertNotEquals(UNKNOWN, instance.getVersion());
    }

    @Test
    void getBuildDate() {
        assertNotNull(instance.getBuild());
        assertNotEquals(UNKNOWN, instance.getBuild());
    }

    @Test
    void getProfile() {
        assertNotNull(instance.getProfile());
        assertNotEquals(UNKNOWN, instance.getProfile());
    }
}