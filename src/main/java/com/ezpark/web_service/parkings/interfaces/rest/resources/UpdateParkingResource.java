package com.ezpark.web_service.parkings.interfaces.rest.resources;

public record UpdateParkingResource(
        Double width,
        Double length,
        Double height,
        Double price,
        String phone,
        Integer space,
        String description,
        String address,
        String numDirection,
        String street,
        String district,
        String city,
        String coordinates,
        Double latitude,
        Double longitude
) {
}
