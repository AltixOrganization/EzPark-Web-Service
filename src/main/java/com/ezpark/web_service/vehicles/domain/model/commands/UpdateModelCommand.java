package com.ezpark.web_service.vehicles.domain.model.commands;

public record UpdateModelCommand(Long modelId,String name, String description, Long brandId) {
}
