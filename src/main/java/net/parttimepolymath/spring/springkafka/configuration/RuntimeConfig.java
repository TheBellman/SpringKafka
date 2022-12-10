package net.parttimepolymath.spring.springkafka.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jcip.annotations.NotThreadSafe;

import java.util.List;

/**
 * Simple Pojo to contain the configuration options derived from the command line.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@NotThreadSafe
public class RuntimeConfig {
    public static enum Mode {CONSUMER, PRODUCER};

    private Mode mode;
    private List<String> bootstraps;

}
