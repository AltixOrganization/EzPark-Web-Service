package com.ezpark.web_service.reservations.application.dtos;

public record ReservationValidationResponse(
        String correlationId,
        Long reservationId,
        boolean exists
) {
}
