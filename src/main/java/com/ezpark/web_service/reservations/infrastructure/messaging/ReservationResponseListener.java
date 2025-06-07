package com.ezpark.web_service.reservations.infrastructure.messaging;



import com.ezpark.web_service.vehicles.application.dtos.VehicleValidationResponse;
import com.ezpark.web_service.parkings.application.dtos.ParkingValidationResponse;


import com.ezpark.web_service.vehicles.infrastructure.messaging.VehicleKafkaConfig;

import com.ezpark.web_service.shared.infrastructure.messaging.ResponseRegistry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationResponseListener {
    private final ResponseRegistry responseRegistry;

    @KafkaListener(
            topics = VehicleKafkaConfig.TOPIC_VEHICLE_VALIDATION_RESPONSE,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleVehicleValidationResponse(VehicleValidationResponse response) {
        try {
            log.info("Respuesta de validación de vehículo recibida: correlationId={}, exists={}",
                    response.correlationId(), response.exists());

            responseRegistry.complete(response.correlationId(), response);

        } catch (Exception e) {
            log.error("Error procesando respuesta de validación de vehículo con correlationId {}: {}",
                    response.correlationId(), e.getMessage(), e);
            // Considerar completar el future con una excepción para notificar al hilo que espera.
            // responseRegistry.completeExceptionally(response.correlationId(), e);
        }
    }


    @KafkaListener(
            // Usamos un string literal por ahora. Idealmente, sería ParkingKafkaConfig.TOPIC_PARKING_VALIDATION_RESPONSE
            topics = "parkings.validation.response",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleParkingValidationResponse(ParkingValidationResponse response) {
        try {
            log.info("Respuesta de validación de parking recibida: correlationId={}, exists={}",
                    response.correlationId(), response.exists());

            responseRegistry.complete(response.correlationId(), response);

        } catch (Exception e) {
            log.error("Error procesando respuesta de validación de parking con correlationId {}: {}",
                    response.correlationId(), e.getMessage(), e);
        }
    }
}