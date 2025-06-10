package com.ezpark.web_service.iam.application.dtos;

import java.time.LocalDate;

public record UserSignedUpEvent(
        Long userId,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email
) {
}
