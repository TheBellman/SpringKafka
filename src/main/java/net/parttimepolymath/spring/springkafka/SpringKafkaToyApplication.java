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

    private final CLIParser parser;
    private final RuntimeConfig runtimeConfig;
    private final Producer<String, String> producer;
    private final Consumer<String, String> consumer;

    public SpringKafkaToyApplication(@Autowired final CLIParser parser, @Autowired final RuntimeConfig runtimeConfig,
                                     @Autowired final Producer<String, String> producer,
                                     @Autowired final Consumer<String, String> consumer) {
        this.parser = parser;
        this.runtimeConfig = runtimeConfig;
        this.producer = producer;
        this.consumer = consumer;
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

        if (runtimeConfig.getMode() == RuntimeConfig.Mode.PRODUCER) {
            producer.execute(runtimeConfig.getCount());
        }

        if (runtimeConfig.getMode() == RuntimeConfig.Mode.CONSUMER) {
            consumer.start();
        }
    }
}
