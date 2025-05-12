package com.ezpark.web_service.parkings.interfaces.rest.resources;

public record UpdateLocationResource(
        String address,
        String numDirection,
        String street,
        String district,
        String city,
        Double latitude,
        Double longitude
) {
}
