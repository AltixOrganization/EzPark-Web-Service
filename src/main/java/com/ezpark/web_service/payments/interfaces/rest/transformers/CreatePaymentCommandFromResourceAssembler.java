package com.ezpark.web_service.payments.interfaces.rest.transformers;

import com.ezpark.web_service.payments.domain.model.commands.CreatePaymentCommand;
import com.ezpark.web_service.payments.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.amount(),
                resource.currency(),
                resource.status(),
                resource.paymentMethod(),
                resource.reservationId()
        );
    }
}
