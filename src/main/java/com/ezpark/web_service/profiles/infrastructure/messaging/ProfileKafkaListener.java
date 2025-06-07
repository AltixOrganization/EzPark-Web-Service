package com.ezpark.web_service.profiles.infrastructure.messaging;

import com.ezpark.web_service.shared.infrastructure.messaging.KafkaEventPublisher;
import com.ezpark.web_service.profiles.application.dtos.ProfileValidationRequest;
import com.ezpark.web_service.profiles.application.dtos.ProfileValidationResponse;
import com.ezpark.web_service.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProfileKafkaListener {

    private final ProfileRepository profileRepository;
    private final KafkaEventPublisher kafkaEventPublisher;

    @KafkaListener(
            topics = ProfileKafkaConfig.TOPIC_PROFILE_VALIDATION_REQUEST,
            groupId = "${profiles.kafka.consumer.group-id:profile-group}"
    )
    public void handleProfileValidationRequest(ProfileValidationRequest request) {
        try {
            log.info("Recibida solicitud de validación para perfil {}", request.profileId());

            boolean exists = profileRepository.existsById(request.profileId());

            ProfileValidationResponse response = new ProfileValidationResponse(
                    request.correlationId(),
                    request.profileId(),
                    exists
            );

            // Publicar la respuesta directamente.
            kafkaEventPublisher.publish(ProfileKafkaConfig.TOPIC_PROFILE_VALIDATION_RESPONSE, response);
            log.info("Enviada respuesta de validación para perfil {}: existe={}", request.profileId(), exists);

        } catch (Exception e) {
            log.error("Error procesando solicitud de validación de perfil para correlationId {}: {}", request.correlationId(), e.getMessage(), e);
            // Aquí podrías manejar el error, por ejemplo, publicando en un topic de errores.
        }
    }
}
