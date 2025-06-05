package com.ezpark.web_service.vehicles.application.internal.queryservices;

import com.ezpark.web_service.vehicles.domain.model.entities.Brand;
import com.ezpark.web_service.vehicles.domain.model.queries.GetAllBrandsQuery;
import com.ezpark.web_service.vehicles.domain.services.BrandQueryService;
import com.ezpark.web_service.vehicles.infrastructure.persistence.jpa.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BrandQueryServiceImpl implements BrandQueryService {
    private BrandRepository brandRepository;

    @Override
    public List<Brand> handle(GetAllBrandsQuery query) {
        return brandRepository.findAll();
    }
}
