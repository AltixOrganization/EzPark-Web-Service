package com.ezpark.web_service.vehicles.domain.model.commands;

public record CreateVehicleCommand(String licensePlate, String model, String brand, Long profileId) {
    public CreateVehicleCommand {
        if (licensePlate == null || licensePlate.isBlank()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        if (profileId == null) {
            throw new IllegalArgumentException("Profile ID cannot be null");
        }
    }
}
