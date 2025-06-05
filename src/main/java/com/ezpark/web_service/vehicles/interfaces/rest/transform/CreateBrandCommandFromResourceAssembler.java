package com.ezpark.web_service.vehicles.interfaces.rest.transform;


import com.ezpark.web_service.vehicles.domain.model.commands.CreateBrandCommand;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.CreateBrandResource;

public class CreateBrandCommandFromResourceAssembler {
    public static CreateBrandCommand toCommandFromResource(CreateBrandResource resource) {
        return new CreateBrandCommand(
                resource.name(),
                resource.description()
        );
    }
}
