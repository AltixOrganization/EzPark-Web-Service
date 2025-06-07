package com.ezpark.web_service.parkings.application.dtos;

public record ParkingQueryRequest(String correlationId, Long entityId, ParkingQueryType queryType) {
}
