package net.parttimepolymath.spring.springkafka.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${default.count}")
    private long defaultCount;


    @Bean
    public RuntimeConfig runtimeConfig() {
        return new RuntimeConfig();
    }

    @Bean
    public CLIParser cliParser(final @Autowired RuntimeConfig config) {
        return new CLIParser(config, defaultCount);
    }
}
