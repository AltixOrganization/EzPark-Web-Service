package com.ezpark.web_service.reservations.domain.events.validation;

public record ReservationValidationRequest(
        String correlationId,
        Long reservationId
) {
}
