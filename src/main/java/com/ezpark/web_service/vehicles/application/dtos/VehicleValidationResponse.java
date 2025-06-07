package com.ezpark.web_service.vehicles.application.dtos;

public record VehicleValidationResponse(
        String correlationId,
        Long vehicleId,
        boolean exists
) {
}
