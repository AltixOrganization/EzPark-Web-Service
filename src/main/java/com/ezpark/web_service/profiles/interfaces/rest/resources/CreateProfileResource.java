package com.ezpark.web_service.profiles.interfaces.rest.resources;

public record CreateProfileResource(
        String name,
        String lastName,
        String address,
        Long userId
) {
}
