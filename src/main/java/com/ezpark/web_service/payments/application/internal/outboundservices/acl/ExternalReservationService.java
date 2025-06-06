package com.ezpark.web_service.payments.application.internal.outboundservices.acl;

import com.ezpark.web_service.reservations.interfaces.acl.ReservationContextFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExternalReservationService {
    private final ReservationContextFacade reservationContextFacade;

    public boolean checkReservationExistById(Long reservationId) {
        return reservationContextFacade.checkReservationExistById(reservationId);
    }

    public boolean markReservationAsApproved(Long reservationId) {
        return reservationContextFacade.markReservationAsApproved(reservationId);
    }
}
