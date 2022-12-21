package net.parttimepolymath.spring.springkafka.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * holder for general beans we want to pop into existence.
 *
 * @author Robert Hook
 * @since 2022-12-11
 */
@Configuration
public class Config {

    @Bean
    public RuntimeConfig runtimeConfig() {
        return new RuntimeConfig();
    }
}
