package net.parttimepolymath.spring.springkafka;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Data stream provider implementation that delivers a stream of random names.
 * @author Robert Hook
 * @since 2022-12-20
 */
@Service
public class StringStreamProvider implements DataStreamProvider<String> {
    private static final Faker faker = new Faker();

    @Override
    public Stream<String> getDataStream() {
        return Stream.generate(() -> faker.name().fullName());
    }
}