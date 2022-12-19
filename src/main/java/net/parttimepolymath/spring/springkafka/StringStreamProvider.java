package net.parttimepolymath.spring.springkafka;

import com.github.javafaker.Faker;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class StringStreamProvider implements DataStreamProvider<String> {
    private static final Faker faker = new Faker();

    @Override
    public Stream<String> getDataStream() {
        Supplier<String> nameSupplier = () -> faker.name().fullName();
        return Stream.generate(nameSupplier);
    }
}