package ru.hehmdalolkek.productaggregator.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration of Kafka topics.
 *
 * @author Inna Badekha
 */
@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Value("${broker.kafka.topics.product-creation}")
    private String productsCreationTopicName;

    @Bean
    public NewTopic productsCreationTopic() {
        return TopicBuilder.name(this.productsCreationTopicName)
                .partitions(10)
                .replicas(1)
                .build();
    }

}
