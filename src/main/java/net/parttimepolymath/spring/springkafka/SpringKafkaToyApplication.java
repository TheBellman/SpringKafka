package net.parttimepolymath.spring.springkafka;

import lombok.extern.slf4j.Slf4j;
import net.parttimepolymath.spring.springkafka.configuration.RuntimeConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

/**
 * top level application starter class.
 *
 * @author Robert Hook
 * @since 2022-12-10
 */
@SpringBootApplication
@Slf4j
public class SpringKafkaToyApplication implements ApplicationRunner {
    private static final String help = """
            usage:
             --help                        print this help message
             --bootstrap-server <broker>   initial server to connect to (e.g. localhost:9092) [REQUIRED]
             --consumer                    run as a data consumer
             --producer                    run as a data producer
             --count <count>               number of messages to produce
             --topic                       topic name used
            """;

    public static void main(String[] args) {
        log.info("Application starting");
        SpringApplication.run(SpringKafkaToyApplication.class, args);
        log.info("Application ending");
    }

    /**
     * try to parse the command line options into something useful. Note that this writes to stderr.
     * @param args the arguments found on the command line
     * @return a RuntimeConfig if the configuration can be determined.
     */
    private Optional<RuntimeConfig> parseArgs(final ApplicationArguments args) {
        if (args.containsOption("help") || !args.containsOption("bootstrap-server") || args.getOptionValues("bootstrap-server").isEmpty()) {
            System.err.println(help);
            return Optional.empty();
        }

        RuntimeConfig config = new RuntimeConfig();

        if (args.containsOption("producer")) {
            config.setMode(RuntimeConfig.Mode.PRODUCER);
        } else if (args.containsOption("consumer")) {
            config.setMode(RuntimeConfig.Mode.CONSUMER);
        } else {
            System.err.println(help);
            return Optional.empty();
        }

        config.setBootstraps(args.getOptionValues("bootstrap-server"));

        return Optional.of(config);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<RuntimeConfig> config = parseArgs(args);
        if (config.isEmpty()) {
            // finish early, we could not determine a configuration
            return;
        }
        log.info(String.valueOf(config.get()));
    }
}
