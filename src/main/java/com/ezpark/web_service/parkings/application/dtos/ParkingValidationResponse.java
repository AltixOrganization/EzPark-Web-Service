package com.ezpark.web_service.parkings.application.dtos;


public record ParkingValidationResponse(
        String correlationId,
        Long parkingId,
        boolean exists
) {
}
