package com.ezpark.web_service.parkings.domain.model.commands;

public record UpdateParkingCommand(
        Long parkingId,
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
        String coordinates,
        Double latitude,
        Double longitude
) {
    public UpdateParkingCommand {
        if (parkingId == null || parkingId <= 0) {
            throw new IllegalArgumentException("Parking ID must be a positive number");
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
        if (space == null || space <= 0) {
            throw new IllegalArgumentException("Space must be a positive number");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        if (numDirection == null || numDirection.isBlank()) {
            throw new IllegalArgumentException("NumDirection cannot be null or empty");
        }
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (district == null || district.isBlank()) {
            throw new IllegalArgumentException("District cannot be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (coordinates == null || coordinates.isBlank()) {
            throw new IllegalArgumentException("Coordinates cannot be null or empty");
        }
        if (latitude == null || latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (longitude == null || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
    }
}