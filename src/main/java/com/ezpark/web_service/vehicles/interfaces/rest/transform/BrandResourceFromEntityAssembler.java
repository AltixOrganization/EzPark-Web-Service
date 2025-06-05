package com.ezpark.web_service.vehicles.interfaces.rest.transform;

import com.ezpark.web_service.vehicles.domain.model.entities.Brand;
import com.ezpark.web_service.vehicles.domain.model.entities.Model;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.BrandWithModelListResource;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.BrandWithModelResource;

public class BrandResourceFromEntityAssembler {

    public static BrandWithModelListResource toResourceFromEntity(Brand brand) {
        return new BrandWithModelListResource(
          brand.getId(),
          brand.getName(),
          brand.getDescription(),
          ModelResourceFromEntityAssembler.toResourceListFromEntityList(brand.getModels())
        );
    }

    public static BrandWithModelResource toResourceFromModel(Model model) {
        return new BrandWithModelResource(
                model.getBrand().getId(),
                model.getBrand().getName(),
                model.getBrand().getDescription(),
                ModelResourceFromEntityAssembler.toResourceFromEntity(model)
        );
    }
}
