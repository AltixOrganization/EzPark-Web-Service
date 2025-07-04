package com.ezpark.web_service.reservations.interfaces.rest.transformers;

import com.ezpark.web_service.reservations.domain.model.commands.UpdateReservationCommand;
import com.ezpark.web_service.reservations.interfaces.rest.resources.UpdateReservationResource;

public class UpdateReservationCommandFromResourceAssembler {
    public static UpdateReservationCommand toCommandFromResource(Long reservationId, UpdateReservationResource resource) {
        return new UpdateReservationCommand(reservationId,
                resource.hoursRegistered(),
                resource.totalFare(),
                resource.reservationDate(),
                resource.startTime(),
                resource.endTime(),
                resource.scheduleId()
        );
    }
}
