package com.ezpark.web_service.profiles.domain.model.commands;

public record CreateProfileCommand(String name, String lastName, String address, Long userId) {
    public CreateProfileCommand {
        //Name starts capital letter and no spaces
        if (name == null || !name.matches("^[A-Z][a-zA-Z]*$")) {
            throw new IllegalArgumentException("Name must start with a capital letter and contain no spaces");
        }
        //Last name starts capital letter and no spaces
        if (lastName == null || !lastName.matches("^[A-Z][a-zA-Z]*$")) {
            throw new IllegalArgumentException("Last name must start with a capital letter and contain no spaces");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
    }
}
