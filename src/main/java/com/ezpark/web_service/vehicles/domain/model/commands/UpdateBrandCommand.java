package com.ezpark.web_service.vehicles.domain.model.commands;

public record UpdateBrandCommand(Long brandId,String name, String description) {
}
