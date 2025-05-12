package com.ezpark.web_service.parkings.interfaces.rest.resources;

public record LocationResource(
        Long id,
        Long parkingId,
        String address,
        String numDirection,
        String street,
        String district,
        String city,
        Double latitude,
        Double longitude
) {}
