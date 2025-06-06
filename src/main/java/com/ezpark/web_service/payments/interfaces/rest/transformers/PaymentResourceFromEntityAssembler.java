package com.ezpark.web_service.payments.interfaces.rest.transformers;


import com.ezpark.web_service.payments.domain.model.aggregates.Payment;
import com.ezpark.web_service.payments.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment payment) {
        return new PaymentResource(
                payment.getId(),
                payment.getAmount(),
                payment.getCurrency().name(),
                payment.getPaymentDate(),
                payment.getStatus().name(),
                payment.getPaymentMethod().name(),
                payment.getReservationId().reservationId(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }
}
