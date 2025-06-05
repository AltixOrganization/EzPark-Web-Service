package com.ezpark.web_service.vehicles.interfaces.rest.resources;

public record ModelResource(
        Long id,
        String name,
        String description,
        Long brandId
) {
}
