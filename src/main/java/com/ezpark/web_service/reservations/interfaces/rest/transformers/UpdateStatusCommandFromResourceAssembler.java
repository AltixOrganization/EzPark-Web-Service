package com.ezpark.web_service.reservations.interfaces.rest.transformers;

import com.ezpark.web_service.reservations.domain.model.commands.UpdateStatusCommand;
import com.ezpark.web_service.reservations.interfaces.rest.resources.UpdateStatusResource;

public class UpdateStatusCommandFromResourceAssembler {
    public static UpdateStatusCommand toCommandFromResource(Long reservationId, UpdateStatusResource resource){
        return new UpdateStatusCommand(reservationId,
                resource.status());
    }
}
