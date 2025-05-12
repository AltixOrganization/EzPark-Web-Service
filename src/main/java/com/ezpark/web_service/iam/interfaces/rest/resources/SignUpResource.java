package com.ezpark.web_service.iam.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SignUpResource(
        @Email
        String email,
        @NotBlank
        String username,
        @NotBlank
        @Size(min = 3, max = 10)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Must contain at least one uppercase letter, one lowercase letter and one number")
        String password,
        List<String> roles) {
}

