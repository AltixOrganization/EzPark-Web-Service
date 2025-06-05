package com.ezpark.web_service.vehicles.interfaces.rest.transform;

import com.ezpark.web_service.vehicles.domain.model.commands.UpdateBrandCommand;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.UpdateBrandResource;

public class UpdateBrandCommandFromResourceAssembler {
    public static UpdateBrandCommand toCommandFromResource(Long brandId, UpdateBrandResource resource) {
        return new UpdateBrandCommand(
                brandId,
                resource.name(),
                resource.description()
        );
    }
}
