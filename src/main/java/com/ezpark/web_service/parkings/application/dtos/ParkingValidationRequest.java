package com.ezpark.web_service.parkings.application.dtos;

public record ParkingValidationRequest(
        String correlationId,
        Long parkingId
) {
}
