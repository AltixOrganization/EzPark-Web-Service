package com.ezpark.web_service.parkings.interfaces.rest.transform;

import com.ezpark.web_service.parkings.domain.model.commands.CreateLocationCommand;
import com.ezpark.web_service.parkings.interfaces.rest.resources.CreateLocationResource;

public class CreateLocationCommandFromResourceAssembler {
    public static CreateLocationCommand fromResource(CreateLocationResource resource) {
        return new CreateLocationCommand(
                resource.address(),
                resource.numDirection(),
                resource.street(),
                resource.district(),
                resource.city(),
                resource.latitude(),
                resource.longitude()
        );
    }
}
