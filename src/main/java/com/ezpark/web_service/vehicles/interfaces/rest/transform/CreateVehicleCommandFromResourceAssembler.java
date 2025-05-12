package com.ezpark.web_service.vehicles.interfaces.rest.transform;

import com.ezpark.web_service.vehicles.domain.model.commands.CreateVehicleCommand;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {
    public static CreateVehicleCommand toCommandFromResource(CreateVehicleResource resource) {
        return new CreateVehicleCommand(
                resource.licensePlate(),
                resource.model(),
                resource.brand(),
                resource.profileId()
        );
    }
}