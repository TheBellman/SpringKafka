package net.parttimepolymath.spring.springkafka.configuration;

/**
 * exception to throw if the command line is unparseable.
 *
 * @author Robert Hook
 * @since 2022-12-20
 */
public class ConfigurationException extends Exception {
    public ConfigurationException(final String message) {
        super(message);
    }
    public ConfigurationException(Exception ex) {
        super(ex);
    }
}
