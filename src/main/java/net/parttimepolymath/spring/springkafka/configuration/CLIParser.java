package net.parttimepolymath.spring.springkafka.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.NumberUtils;

import java.util.List;

/**
 * helper class to parse the command line arguments and set up the runtime configuration.
 */
public class CLIParser {

    private long defaultCount;
    private static final String help = """
            usage:
             --help                        print this help message
             --bootstrap-server <broker>   initial server to connect to (e.g. localhost:9092) [REQUIRED]
             --consumer                    run as a data consumer
             --producer                    run as a data producer
             --count <count>               number of messages to produce
             --topic                       topic name used
            """;
    private final RuntimeConfig runtimeConfig;

    /**
     * primary constructor
     *
     * @param runtimeConfig the runtime config that will be set up.
     * @param defaultCount the default count that is used if nothing is provided
     */
    public CLIParser(final @Autowired RuntimeConfig runtimeConfig, final long defaultCount) {
        this.runtimeConfig = runtimeConfig;
        this.defaultCount = defaultCount;
    }

    /**
     * try to parse the command line options into something useful. Note that this writes to stderr.
     * @param args the arguments found on the command line
     * @return true if the arguments were good, false otherwise
     */
    public boolean parseArgs(final ApplicationArguments args) {
        boolean hasArg = args.containsOption("bootstrap-server");
        List<String> bs = args.getOptionValues("bootstrap-server");
        if (args.containsOption("help") || !args.containsOption("bootstrap-server")) {
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

        try {
            runtimeConfig.setBootstraps(validateBootstraps(args.getOptionValues("bootstrap-server")));
        } catch (ConfigurationException e) {
            System.err.println(help);
            return false;
        }

        return true;
    }

    /**
     * verify that all the items in the bootstrap list are of the form host:port, and that the list is not empty
     * @param bootstrap the list of bootstrap options to check
     * @return the provided list
     * @throws ConfigurationException if the items in the list are not good, or the list is empty.
     */
    private List<String> validateBootstraps(final List<String> bootstrap) throws ConfigurationException {
        if ( bootstrap.isEmpty()) {
            throw new ConfigurationException("expected bootstraps to be specified");
        }

        for (String s : bootstrap) {
            String[] parts = s.split(":");
            if (parts.length != 2) {
                throw new ConfigurationException("expected the bootstrap to be host:port");
            }

            try {
                NumberUtils.parseNumber(parts[1], Long.class);
            } catch (IllegalArgumentException ex) {
                throw new ConfigurationException("expected the bootstrap to be host:port");
            }
        }
        return bootstrap;
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
}
