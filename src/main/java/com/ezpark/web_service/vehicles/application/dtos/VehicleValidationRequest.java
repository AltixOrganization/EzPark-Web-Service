package com.ezpark.web_service.vehicles.application.dtos;

public record VehicleValidationRequest(
        String correlationId,
        Long vehicleId
) {
}
