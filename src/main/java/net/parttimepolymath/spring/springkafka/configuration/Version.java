package net.parttimepolymath.spring.springkafka.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * simple POJO to contain some properties injected from the maven build.
 *
 * @since 2022-12-10
 * @author Robert Hook
 */

@Data
@Configuration
@ConfigurationProperties(prefix="app.version")
public class Version {
    public static final String UNKNOWN="unknown";

    /**
     * The name of the application.
     */
    @NotBlank
    private String name=UNKNOWN;

    /**
     * The version of the application.
     */
    @NotBlank
    private String version=UNKNOWN;

    /**
     * The date and time of the artifact build.
     */
    @NotBlank
    private String build=UNKNOWN;

    /**
     * the active spring profile. which should align to the maven profile.
     */
    @NotBlank
    private String profile=UNKNOWN;
}
