package com.ezpark.web_service.reservations.domain.events.validation;

public record ReservationValidationResponse(
        String correlationId,
        Long reservationId,
        boolean exists
) {
}
