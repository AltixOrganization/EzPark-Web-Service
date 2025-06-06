package com.ezpark.web_service.reservations.interfaces.acl;

public interface ReservationContextFacade {
    boolean checkReservationExistById(Long reservationId);
    boolean markReservationAsApproved(Long reservationId);
}
