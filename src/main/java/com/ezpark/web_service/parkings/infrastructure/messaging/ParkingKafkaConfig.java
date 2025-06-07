package com.ezpark.web_service.parkings.infrastructure.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ParkingKafkaConfig {
    public static final String TOPIC_PARKING_VALIDATION_REQUEST = "parkings.validation.request";
    public static final String TOPIC_PARKING_VALIDATION_RESPONSE = "parkings.validation.response";

    public static final String TOPIC_PARKING_CREATED = "parkings.created";
    public static final String TOPIC_PARKING_UPDATED = "parkings.updated";
    public static final String TOPIC_PARKING_DELETED = "parkings.deleted";

    @Bean
    public NewTopic parkingValidationRequestTopic() {
        return TopicBuilder.name(TOPIC_PARKING_VALIDATION_REQUEST)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic parkingValidationResponseTopic() {
        return TopicBuilder.name(TOPIC_PARKING_VALIDATION_RESPONSE)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic parkingsCreatedTopic() {
        return TopicBuilder.name(TOPIC_PARKING_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic parkingsUpdatedTopic() {
        return TopicBuilder.name(TOPIC_PARKING_UPDATED)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic parkingsDeletedTopic() {
        return TopicBuilder.name(TOPIC_PARKING_DELETED)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
