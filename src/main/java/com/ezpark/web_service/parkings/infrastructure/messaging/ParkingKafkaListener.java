package com.ezpark.web_service.parkings.infrastructure.messaging;

import com.ezpark.web_service.shared.infrastructure.messaging.KafkaEventPublisher;
import com.ezpark.web_service.parkings.application.dtos.ParkingValidationRequest;
import com.ezpark.web_service.parkings.application.dtos.ParkingValidationResponse;
import com.ezpark.web_service.parkings.infrastructure.persistence.jpa.repositories.ParkingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParkingKafkaListener {

    private final ParkingRepository parkingRepository;
    private final KafkaEventPublisher eventPublisher;

    @KafkaListener(
            topics = ParkingKafkaConfig.TOPIC_PARKING_VALIDATION_REQUEST,
            groupId = "${parkings.kafka.consumer.group-id:parking-group}"
    )
    public void handleParkingValidationRequest(ParkingValidationRequest request) {
        try {
            log.info("Recibida solicitud de validación para parking {}", request.parkingId());

            boolean exists = parkingRepository.existsById(request.parkingId());

            ParkingValidationResponse response = new ParkingValidationResponse(
                    request.correlationId(),
                    request.parkingId(),
                    exists
            );

            // Publicar el objeto de respuesta directamente.
            eventPublisher.publish(ParkingKafkaConfig.TOPIC_PARKING_VALIDATION_RESPONSE, response);
            log.info("Enviada respuesta de validación para parking {}: existe={}", request.parkingId(), exists);

        } catch (Exception e) {
            log.error("Error procesando solicitud de validación de parking para correlationId {}: {}", request.correlationId(), e.getMessage(), e);
            // Aquí podrías publicar en un topic de errores (DLT) o manejarlo de otra forma.
        }
    }
}
