package com.ezpark.web_service.parkings.application.dtos;

public record ParkingCommandResponse(String correlationId, boolean success, String message) {
}
