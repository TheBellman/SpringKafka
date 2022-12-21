package net.parttimepolymath.spring.springkafka;

import lombok.extern.slf4j.Slf4j;
import net.parttimepolymath.spring.springkafka.avro.Customer;
import net.parttimepolymath.spring.springkafka.services.CLIParser;
import net.parttimepolymath.spring.springkafka.configuration.RuntimeConfig;
import net.parttimepolymath.spring.springkafka.services.ConsumerService;
import net.parttimepolymath.spring.springkafka.services.ProducerService;
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
    private final ProducerService<String, Customer> producerService;
    private final ConsumerService<String, Customer> consumerService;

    public SpringKafkaToyApplication(@Autowired final CLIParser parser,
                                     @Autowired final RuntimeConfig runtimeConfig,
                                     @Autowired final ProducerService<String, Customer> producerService,
                                     @Autowired final ConsumerService<String, Customer> consumerService) {
        this.parser = parser;
        this.runtimeConfig = runtimeConfig;
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaToyApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!parser.parseArgs(args)) {
            return;
        }

        if (runtimeConfig.getMode() == RuntimeConfig.Mode.PRODUCER) {
            producerService.execute(runtimeConfig.getCount());
        }

        if (runtimeConfig.getMode() == RuntimeConfig.Mode.CONSUMER) {
            consumerService.start();
        }
    }
}
