package com.ezpark.web_service.vehicles.interfaces.rest.transform;


import com.ezpark.web_service.vehicles.domain.model.commands.CreateModelCommand;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.CreateModelResource;

public class CreateModelCommandFromResourceAssembler {
    public static CreateModelCommand toCommandFromResource(CreateModelResource resource) {
        return new CreateModelCommand(
                resource.name(),
                resource.description(),
                resource.brandId()
        );
    }
}
