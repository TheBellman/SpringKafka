package net.parttimepolymath.spring.springkafka.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Key generator that returns a random UUID as a string.
 * @author Robert Hook
 * @since 2022-12-20
 */

@Service
public class UUIDKeyGenerator implements KeyGenerator<String> {
    @Override
    public String getKey() {
        return UUID.randomUUID().toString();
    }
}
