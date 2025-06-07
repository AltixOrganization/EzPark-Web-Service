package com.ezpark.web_service.profiles.application.dtos;

public record ProfileValidationRequest(
        String correlationId,
        Long profileId
) {
}
