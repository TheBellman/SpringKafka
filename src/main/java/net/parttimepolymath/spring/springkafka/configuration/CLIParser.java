package net.parttimepolymath.spring.springkafka.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

/**
 * helper class to parse the command line arguments and set up the runtime configuration.
 *
 * @author Robert Hook
 * @since 2022-12-20
 */
@Service
public class CLIParser {

    @Value("${default.count:1000}")
    private long defaultCount;
    private static final String help = """
            usage:
             --help                        print this help message
             --consumer                    run as a data consumer
             --producer                    run as a data producer
             --count <count>               number of messages to produce
            """;
    private final RuntimeConfig runtimeConfig;

    /**
     * primary constructor
     *
     * @param runtimeConfig the runtime config that will be set up.
     */
    public CLIParser(final @Autowired RuntimeConfig runtimeConfig) {
        this.runtimeConfig = runtimeConfig;
    }

    /**
     * try to parse the command line options into something useful. Note that this writes to stderr.
     * @param args the arguments found on the command line
     * @return true if the arguments were good, false otherwise
     */
    public boolean parseArgs(final ApplicationArguments args) {
        if (args.containsOption("help")) {
            System.err.println(help);
            return false;
        }

        if (args.containsOption("producer")) {
            runtimeConfig.setMode(RuntimeConfig.Mode.PRODUCER);
            try {
                runtimeConfig.setCount(parseCount(args));
            } catch (ConfigurationException e) {
                System.err.println(help);
                return false;
            }
        } else if (args.containsOption("consumer")) {
            runtimeConfig.setMode(RuntimeConfig.Mode.CONSUMER);
        } else {
            System.err.println(help);
            return false;
        }

        return true;
    }


    /**
     * work out how many messages to produce based on command line.
     * @param args the command line to parse.
     * @return the number of messages to produce, which defaults to default.count if not specified
     * @throws  ConfigurationException if we can't parse at all
     */
    private long parseCount(final ApplicationArguments args) throws ConfigurationException {
        if (args.containsOption("count")) {
            if (args.getOptionValues("count").isEmpty()) {
                throw new ConfigurationException("Count needs a value specified");
            }

            try {
                return NumberUtils.parseNumber(args.getOptionValues("count").get(0), Long.class);
            } catch (IllegalArgumentException ex) {
                throw new ConfigurationException(ex);
            }
        } else {
            return defaultCount;
        }
    }

    /**
     * override the default - only used for testing
     * @param defaultCount the new default to use
     */
    public void setDefaultCount(long defaultCount) {
        this.defaultCount = defaultCount;
    }
}
