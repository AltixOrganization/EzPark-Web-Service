package com.ezpark.web_service.vehicles.interfaces.rest.resources;

public record UpdateVehicleResource(
        String licensePlate,
        String model,
        String brand
) {
}
