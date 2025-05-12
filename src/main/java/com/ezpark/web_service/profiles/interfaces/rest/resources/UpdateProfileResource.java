package com.ezpark.web_service.profiles.interfaces.rest.resources;

public record UpdateProfileResource(
        String name,
        String lastName,
        String address,
        String email,
        String idCardType,
        String idCardNumber
) {
}
