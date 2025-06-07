package com.ezpark.web_service.reservations.application.dtos;

public record ReservationValidationRequest(
        String correlationId,
        Long reservationId
) {
}
