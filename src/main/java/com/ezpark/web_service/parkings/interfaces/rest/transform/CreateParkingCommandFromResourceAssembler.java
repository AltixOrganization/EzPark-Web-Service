package com.ezpark.web_service.parkings.interfaces.rest.transform;

import com.ezpark.web_service.parkings.domain.model.commands.CreateParkingCommand;
import com.ezpark.web_service.parkings.interfaces.rest.resources.CreateParkingResource;

public class CreateParkingCommandFromResourceAssembler {
    public static CreateParkingCommand toCommandFromResource(CreateParkingResource resource) {
        return new CreateParkingCommand(resource.profileId(),
                resource.width(),
                resource.length(),
                resource.height(),
                resource.price(),
                resource.phone(),
                resource.space(),
                resource.description(),
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
