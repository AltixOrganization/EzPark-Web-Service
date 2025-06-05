package com.ezpark.web_service.vehicles.interfaces.rest.transform;

import com.ezpark.web_service.vehicles.domain.model.entities.Model;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.ModelResource;

import java.util.List;

public class ModelResourceFromEntityAssembler {
    public static ModelResource toResourceFromEntity(Model model) {
        return new ModelResource(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getBrand().getId()
        );
    }

    public static List<ModelResource> toResourceListFromEntityList(List<Model> models) {
        return models.stream()
                .map(ModelResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }
}
