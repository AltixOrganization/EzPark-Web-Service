package com.ezpark.web_service.reservations.infrastructure.messaging;

// Importamos los nuevos DTOs
import com.ezpark.web_service.reservations.domain.events.validation.ReservationValidationRequest;
import com.ezpark.web_service.reservations.domain.events.validation.ReservationValidationResponse;
import com.ezpark.web_service.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.ezpark.web_service.shared.infrastructure.messaging.KafkaEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationKafkaListener {

    private final ReservationRepository reservationRepository;
    private final KafkaEventPublisher eventPublisher;

    @KafkaListener(
            topics = ReservationKafkaConfig.TOPIC_RESERVATION_VALIDATION_REQUEST,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleReservationValidationRequest(ReservationValidationRequest request) {
        try {
            log.info("Recibida solicitud de validación para reserva: {}", request.reservationId());

            boolean exists = reservationRepository.existsById(request.reservationId());

            ReservationValidationResponse response = new ReservationValidationResponse(
                    request.correlationId(),
                    request.reservationId(),
                    exists
            );

            eventPublisher.publish(ReservationKafkaConfig.TOPIC_RESERVATION_VALIDATION_RESPONSE, response);

            log.info("Respuesta de validación de reservación enviada: correlationId={}, exists={}",
                    response.correlationId(), response.exists());

        } catch (Exception e) {
            log.error("Error procesando solicitud de validación de reservación con correlationId {}: {}",
                    request.correlationId(), e.getMessage(), e);
        }
    }
}
