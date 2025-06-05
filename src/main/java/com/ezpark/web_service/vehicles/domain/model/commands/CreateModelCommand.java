package com.ezpark.web_service.vehicles.domain.model.commands;

public record CreateModelCommand(String name, String description, Long brandId) {
}
