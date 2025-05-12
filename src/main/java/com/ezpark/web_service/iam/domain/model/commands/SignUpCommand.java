package com.ezpark.web_service.iam.domain.model.commands;

import com.ezpark.web_service.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(
        String email,
        String username,
        String password,
        List<Role> roles) {
    public SignUpCommand {

        if (email == null || email.isBlank() ) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email format is not valid");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (password.length() < 3 || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            throw new IllegalArgumentException("Password must be at least 3 characters long and contain at least one special character");
        }
    }
}
