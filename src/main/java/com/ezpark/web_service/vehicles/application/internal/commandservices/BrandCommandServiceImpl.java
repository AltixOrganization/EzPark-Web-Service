package com.ezpark.web_service.vehicles.application.internal.commandservices;


import com.ezpark.web_service.vehicles.domain.model.commands.CreateBrandCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.DeleteBrandCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.UpdateBrandCommand;
import com.ezpark.web_service.vehicles.domain.model.entities.Brand;
import com.ezpark.web_service.vehicles.domain.model.exceptions.BrandNotFoundException;
import com.ezpark.web_service.vehicles.domain.services.BrandCommandService;
import com.ezpark.web_service.vehicles.infrastructure.persistence.jpa.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BrandCommandServiceImpl implements BrandCommandService {
    private BrandRepository brandRepository;

    @Override
    public Optional<Brand> handle(CreateBrandCommand command) {
        var brand = new Brand(command);
        brandRepository.save(brand);
        return Optional.of(brand);
    }

    @Override
    public Optional<Brand> handle(UpdateBrandCommand command) {
        var brand = brandRepository.findById(command.brandId());
        if (brand.isEmpty()) {
            throw new BrandNotFoundException();
        }
        brand.get().update(command);
        brandRepository.save(brand.get());
        return brand;
    }

    @Override
    public void handle(DeleteBrandCommand command) {
        if (!brandRepository.existsById(command.id())){
            throw new BrandNotFoundException();
        }
        brandRepository.deleteById(command.id());
    }
}
