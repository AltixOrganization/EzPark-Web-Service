package com.ezpark.web_service.payments.application.internal.outboundservices.acl;

public interface ReservationContextFacade {
    void approveReservation(Long reservationId) throws Exception;
}
