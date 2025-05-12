package com.ezpark.web_service.parkings.domain.model.commands;

public record CreateParkingCommand(
        Long profileId,
        Double width,
        Double length,
        Double height,
        Double price,
        String phone,
        Integer space,
        String description,
        String address,
        String numDirection,
        String street,
        String district,
        String city,
        Double latitude,
        Double longitude
) {
    public CreateParkingCommand {

        if (profileId == null || profileId <= 0) {
            throw new IllegalArgumentException("Profile ID must be a positive number");
        }
        if (width == null || width <= 0) {
            throw new IllegalArgumentException("Width must be a positive number");
        }
        if (length == null || length <= 0) {
            throw new IllegalArgumentException("Length must be a positive number");
        }
        if (height == null || height <= 0) {
            throw new IllegalArgumentException("Height must be a positive number");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be a positive number");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }
        if (space == null || space <= 0) {
            throw new IllegalArgumentException("Space must be a positive number");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
        if (numDirection == null || numDirection.isBlank()) {
            throw new IllegalArgumentException("Number direction cannot be null or blank");
        }
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if (district == null || district.isBlank()) {
            throw new IllegalArgumentException("District cannot be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }
        if (latitude == null || latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (longitude == null || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
    }
}