package com.ezpark.web_service.vehicles.interfaces.rest.resources;


public record VehicleResource(
        Long id,
        String licensePlate,
        String model,
        String brand,
        String profileId
) {
}
