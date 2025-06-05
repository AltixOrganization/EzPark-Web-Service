package com.ezpark.web_service.vehicles.domain.services;

import com.ezpark.web_service.vehicles.domain.model.commands.CreateBrandCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.DeleteBrandCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.UpdateBrandCommand;
import com.ezpark.web_service.vehicles.domain.model.entities.Brand;

import java.util.Optional;


public interface BrandCommandService {
    Optional<Brand> handle(CreateBrandCommand command);
    Optional<Brand> handle(UpdateBrandCommand command);
    void handle(DeleteBrandCommand command);
}
