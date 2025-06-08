package com.ezpark.web_service.parkings.infrastructure.messaging;

import com.ezpark.web_service.profiles.application.dtos.ProfileValidationResponse;
import com.ezpark.web_service.profiles.infrastructure.messaging.ProfileKafkaConfig;
import com.ezpark.web_service.shared.infrastructure.messaging.ResponseRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParkingResponseListener {
    private final ResponseRegistry responseRegistry;

    @KafkaListener(
            topics = ProfileKafkaConfig.TOPIC_PROFILE_VALIDATION_RESPONSE,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleProfileValidationResponse(ProfileValidationResponse response) {
        try {
            log.info("Respuesta de validación de perfil recibida: correlationId={}, exists={}",
                    response.correlationId(), response.exists());

            responseRegistry.complete(response.correlationId(), response);

        } catch (Exception e) {
            log.error("Error procesando respuesta de validación de perfil con correlationId {}: {}",
                    response.correlationId(), e.getMessage(), e);
            // Considerar completar el future con una excepción para notificar al hilo que espera.
            // responseRegistry.completeExceptionally(response.correlationId(), e);
        }
    }
}
