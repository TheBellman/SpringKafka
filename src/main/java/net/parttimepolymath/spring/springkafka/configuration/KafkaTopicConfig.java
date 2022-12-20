package net.parttimepolymath.spring.springkafka.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

/**
 * This class wires to the magic kafka admin client and will try to create a topic at run time.
 * Note that we use the profile ID we inherit from the pom.xml via application.properties to
 * ensure that the tests - which are marked as using the test profile - don't cause this to fire
 * and try to connect to a cluster
 *
 * @author Robert Hook
 * @since 2022-12-20
 */

@Slf4j
@Configuration
@Profile({ "development" })
public class KafkaTopicConfig {

    @Value("${topic.name}")
    private String targetTopic;

    @Bean
    public NewTopic targetTopic() {
        return TopicBuilder.name(targetTopic).partitions(1).replicas(1).build();
    }
}
