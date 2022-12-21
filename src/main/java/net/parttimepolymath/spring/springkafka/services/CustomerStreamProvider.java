package net.parttimepolymath.spring.springkafka.services;

import com.github.javafaker.Faker;
import net.parttimepolymath.spring.springkafka.avro.Customer;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * Data stream provider implementation that delivers a stream of random names.
 * @author Robert Hook
 * @since 2022-12-20
 */
@Service
public class CustomerStreamProvider implements DataStreamProvider<Customer> {
    private static final Faker faker = new Faker();

    @Override
    public Stream<Customer> getDataStream() {
        return Stream.generate(() -> Customer.newBuilder().setId(UUID.randomUUID().toString()).setName(faker.name().fullName()).build());
    }
}