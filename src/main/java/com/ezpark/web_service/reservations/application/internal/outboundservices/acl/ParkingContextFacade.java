package com.ezpark.web_service.reservations.application.internal.outboundservices.acl;

public interface ParkingContextFacade {
    boolean checkParkingExistById(Long parkingId) throws Exception;
}
