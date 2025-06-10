package com.ezpark.web_service.reservations.application.dtos;

public record ReservationCommandResponse(
        String correlationId,
        boolean success,
        String message
) {
}
