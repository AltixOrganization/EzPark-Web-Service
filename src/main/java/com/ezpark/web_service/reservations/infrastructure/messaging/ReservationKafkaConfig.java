package com.ezpark.web_service.reservations.infrastructure.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ReservationKafkaConfig {
    // --- Topics para el servicio de Validación de Reservas (Request-Reply) ---
    public static final String TOPIC_RESERVATION_VALIDATION_REQUEST = "reservations.validation.request";
    public static final String TOPIC_RESERVATION_VALIDATION_RESPONSE = "reservations.validation.response";

    public static final String TOPIC_RESERVATION_CREATED = "reservations.created";
    public static final String TOPIC_RESERVATION_CANCELED = "reservations.canceled";

    @Bean
    public NewTopic reservationValidationRequestTopic() {
        return TopicBuilder.name(TOPIC_RESERVATION_VALIDATION_REQUEST)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic reservationValidationResponseTopic() {
        return TopicBuilder.name(TOPIC_RESERVATION_VALIDATION_RESPONSE)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic reservationsCreatedTopic() {
        return TopicBuilder.name(TOPIC_RESERVATION_CREATED)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic reservationsCanceledTopic() {
        return TopicBuilder.name(TOPIC_RESERVATION_CANCELED)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
