package net.parttimepolymath.spring.springkafka;

/**
 * interface which allows us to abstract keys in the Producer
 * @param <K> the type of Key to create
 *
 * @author Robert Hook
 * @since 2022-12-20
 */
public interface KeyGenerator<K> {
    /**
     * get a key of type K
     * @return a generated key of type K
     */
    K getKey();
}
