package net.parttimepolymath.spring.springkafka.services;

import lombok.extern.slf4j.Slf4j;
import net.parttimepolymath.spring.springkafka.configuration.Version;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Report some version information during startup of the application.
 * @author Robert Hook
 * @since 2022-12-20
 */
@Component
@Slf4j
public class VersionConsumer implements InitializingBean {
    private final Version version;

    public VersionConsumer(@Autowired Version version) {
        this.version = version;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Version details : {}", version);
    }
}
