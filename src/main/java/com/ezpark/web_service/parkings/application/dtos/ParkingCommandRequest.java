package com.ezpark.web_service.parkings.application.dtos;

public record ParkingCommandRequest(String correlationId, Long scheduleId, ParkingCommandType commandType) {
}
