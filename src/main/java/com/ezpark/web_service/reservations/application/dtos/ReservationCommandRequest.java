package com.ezpark.web_service.reservations.application.dtos;

public record ReservationCommandRequest(
        String correlationId,
        Long reservationId,
        CommandType commandType
) {
}
