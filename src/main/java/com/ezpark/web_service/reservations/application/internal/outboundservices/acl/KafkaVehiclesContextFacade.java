package com.ezpark.web_service.reservations.application.internal.outboundservices.acl;

// Dependencias de infraestructura compartida
import com.ezpark.web_service.shared.infrastructure.messaging.KafkaEventPublisher;
import com.ezpark.web_service.shared.infrastructure.messaging.ResponseRegistry;

// Dependencias del CONTRATO PÚBLICO del módulo 'vehicles'
import com.ezpark.web_service.vehicles.application.dtos.VehicleValidationRequest;
import com.ezpark.web_service.vehicles.application.dtos.VehicleValidationResponse;
import com.ezpark.web_service.vehicles.infrastructure.messaging.VehicleKafkaConfig;

// Anotaciones de Spring y Lombok
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaVehiclesContextFacade implements VehiclesContextFacade {

    private final KafkaEventPublisher eventPublisher;
    private final ResponseRegistry responseRegistry;

    @Override
    public boolean checkVehicleExistsById(Long vehicleId) throws Exception {
        String correlationId = UUID.randomUUID().toString();

        VehicleValidationRequest request = new VehicleValidationRequest(correlationId, vehicleId);

        try {
            log.info("Enviando solicitud de validación para vehículo {} con correlationId {}",
                    request.vehicleId(), request.correlationId());

            eventPublisher.publish(VehicleKafkaConfig.TOPIC_VEHICLE_VALIDATION_REQUEST, request);

            VehicleValidationResponse response = responseRegistry.waitForResponse(correlationId, VehicleValidationResponse.class);

            log.info("Respuesta de validación recibida para vehículo {} (correlationId {}): existe={}",
                    response.vehicleId(), response.correlationId(), response.exists());

            return response.exists();

        } catch (TimeoutException e) {
            log.error("Timeout esperando la respuesta de validación para el vehículo {} (correlationId {})",
                    vehicleId, correlationId, e);
            throw new Exception("Timeout al validar el vehículo. El servicio de vehículos podría no estar disponible.", e);

        } catch (Exception e) {
            log.error("Error inesperado durante la validación del vehículo {} (correlationId {})",
                    vehicleId, correlationId, e);
            throw new Exception("Error inesperado al comunicarse con el servicio de vehículos.", e);
        }
    }
}
