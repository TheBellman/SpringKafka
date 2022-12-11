package net.parttimepolymath.spring.springkafka;

import lombok.extern.slf4j.Slf4j;
import net.parttimepolymath.spring.springkafka.configuration.CLIParser;
import net.parttimepolymath.spring.springkafka.configuration.RuntimeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * top level application starter class.
 *
 * @author Robert Hook
 * @since 2022-12-10
 */
@SpringBootApplication
@Slf4j
public class SpringKafkaToyApplication implements ApplicationRunner {

    private CLIParser parser;

    private RuntimeConfig runtimeConfig;

    @Autowired
    public void setParser(final CLIParser parser) {
        this.parser = parser;
    }

    @Autowired
    public void setRuntimeConfig(final RuntimeConfig runtimeConfig) {
        this.runtimeConfig = runtimeConfig;
    }

    public static void main(String[] args) {
        log.info("Application starting");
        SpringApplication.run(SpringKafkaToyApplication.class, args);
        log.info("Application ending");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!parser.parseArgs(args)) {
            return;
        }

        log.info("Good arguments! will run now with {}", runtimeConfig);
    }
}
