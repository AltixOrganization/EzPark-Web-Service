package com.ezpark.web_service.vehicles.domain.services;

import com.ezpark.web_service.vehicles.domain.model.entities.Brand;
import com.ezpark.web_service.vehicles.domain.model.queries.GetAllBrandsQuery;

import java.util.List;

public interface BrandQueryService {
    List<Brand> handle(GetAllBrandsQuery query);
}
