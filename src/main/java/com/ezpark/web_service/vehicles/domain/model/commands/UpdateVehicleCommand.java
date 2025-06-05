package com.ezpark.web_service.vehicles.domain.model.commands;

public record UpdateVehicleCommand(Long vehicleId, String licensePlate, Long modelId) {
}
