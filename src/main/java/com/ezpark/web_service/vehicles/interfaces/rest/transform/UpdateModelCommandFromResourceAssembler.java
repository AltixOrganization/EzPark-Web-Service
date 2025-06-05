package com.ezpark.web_service.vehicles.interfaces.rest.transform;


import com.ezpark.web_service.vehicles.domain.model.commands.UpdateModelCommand;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.UpdateModelResource;

public class UpdateModelCommandFromResourceAssembler {
    public static UpdateModelCommand toCommandFromResource(Long modelId, UpdateModelResource resource) {
        return new UpdateModelCommand(
                modelId,
                resource.name(),
                resource.description(),
                resource.brandId()
        );
    }
}
