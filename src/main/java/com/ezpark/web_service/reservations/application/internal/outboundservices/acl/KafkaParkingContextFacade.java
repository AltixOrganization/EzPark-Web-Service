package com.ezpark.web_service.reservations.application.internal.outboundservices.acl;

// Dependencias de infraestructura compartida
import com.ezpark.web_service.shared.infrastructure.messaging.KafkaEventPublisher;
import com.ezpark.web_service.shared.infrastructure.messaging.ResponseRegistry;

// Dependencias del CONTRATO PÚBLICO del módulo 'parking'
import com.ezpark.web_service.parkings.application.dtos.ParkingValidationRequest;
import com.ezpark.web_service.parkings.application.dtos.ParkingValidationResponse;
import com.ezpark.web_service.parkings.infrastructure.messaging.ParkingKafkaConfig;


// Anotaciones de Spring y Lombok
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaParkingContextFacade implements ParkingContextFacade {

    private final KafkaEventPublisher eventPublisher;
    private final ResponseRegistry responseRegistry;

    @Override
    public boolean checkParkingExistById(Long parkingId) throws Exception {
        String correlationId = UUID.randomUUID().toString();

        ParkingValidationRequest request = new ParkingValidationRequest(correlationId, parkingId);

        try {
            log.info("Enviando solicitud de validación para parking {} con correlationId {}",
                    request.parkingId(), request.correlationId());

            eventPublisher.publish(ParkingKafkaConfig.TOPIC_PARKING_VALIDATION_REQUEST, request);

            ParkingValidationResponse response = responseRegistry.waitForResponse(correlationId, ParkingValidationResponse.class);

            log.info("Respuesta de validación recibida para parking {} (correlationId {}): existe={}",
                    response.parkingId(), response.correlationId(), response.exists());

            return response.exists();

        } catch (TimeoutException e) {
            log.error("Timeout esperando la respuesta de validación para el parking {} (correlationId {})",
                    parkingId, correlationId, e);
            throw new Exception("Timeout al validar el parking. El servicio de parkings podría no estar disponible.", e);

        } catch (Exception e) {
            log.error("Error inesperado durante la validación del parking {} (correlationId {})",
                    parkingId, correlationId, e);
            throw new Exception("Error inesperado al comunicarse con el servicio de parkings.", e);
        }
    }
}
