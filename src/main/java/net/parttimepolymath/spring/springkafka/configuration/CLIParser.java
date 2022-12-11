package net.parttimepolymath.spring.springkafka.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

import java.util.List;

/**
 * helper class to parse the command line arguments and set up the runtime configuration.
 */
public class CLIParser {
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
        boolean hasArg = args.containsOption("bootstrap-server");
        List<String> bs = args.getOptionValues("bootstrap-server");
        if (args.containsOption("help") || !args.containsOption("bootstrap-server") || args.getOptionValues("bootstrap-server").isEmpty()) {
            System.err.println(help);
            return false;
        }
        if (args.containsOption("producer")) {
            runtimeConfig.setMode(RuntimeConfig.Mode.PRODUCER);
        } else if (args.containsOption("consumer")) {
            runtimeConfig.setMode(RuntimeConfig.Mode.CONSUMER);
        } else {
            System.err.println(help);
            return false;
        }

        runtimeConfig.setBootstraps(args.getOptionValues("bootstrap-server"));

        return true;
    }
}
