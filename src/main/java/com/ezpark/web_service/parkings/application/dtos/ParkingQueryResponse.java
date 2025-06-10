package com.ezpark.web_service.parkings.application.dtos;

public record ParkingQueryResponse(String correlationId, boolean result, String message) {
}
