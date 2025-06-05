package com.ezpark.web_service.vehicles.interfaces.rest.resources;


public record BrandWithModelResource(
        Long id,
        String name,
        String description,
        ModelResource model
) {
}
