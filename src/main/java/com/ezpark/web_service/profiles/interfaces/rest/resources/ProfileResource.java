package com.ezpark.web_service.profiles.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String name,
        String lastName,
        String address,
        Long userId
) {
}
