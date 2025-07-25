package com.ezpark.web_service.reservations.interfaces.rest.resources;

import com.ezpark.web_service.reservations.domain.model.valueobject.Status;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusResource(
        @NotNull(message = "{reservation.status.not.null}")
        Status status
) {
}
