package com.ezpark.web_service.reservations.application.internal.outboundservices.acl;

public interface VehiclesContextFacade {
    boolean checkVehicleExistsById(Long vehicleId) throws Exception;
}
