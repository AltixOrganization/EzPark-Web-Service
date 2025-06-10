package com.ezpark.web_service.profiles.application.dtos;

public record ProfileValidationResponse(
        String correlationId,
        Long profileId,
        boolean exists
) {
}
