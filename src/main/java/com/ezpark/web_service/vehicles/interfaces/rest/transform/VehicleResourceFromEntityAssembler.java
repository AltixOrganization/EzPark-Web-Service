package com.ezpark.web_service.vehicles.interfaces.rest.transform;


import com.ezpark.web_service.vehicles.domain.model.aggregates.Vehicle;
import com.ezpark.web_service.vehicles.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle entity) {
        return new VehicleResource(
                entity.getId(),
                entity.getLicensePlate(),
                entity.getBrand(),
                entity.getModel(),
                entity.getProfileId().profileId().toString(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
