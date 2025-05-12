package com.ezpark.web_service.parkings.interfaces.rest.transform;

import com.ezpark.web_service.parkings.domain.model.aggregates.Parking;
import com.ezpark.web_service.parkings.interfaces.rest.resources.ParkingResource;

public class ParkingResourceFromEntityAssembler {
    public static ParkingResource toResourceFromEntity(Parking entity){
        String[] scheduleData = entity.getSchedules().stream()
                .map(schedule -> new String[]{
                        schedule.getDay().toString(),
                        schedule.getStartTime().toString(),
                        schedule.getEndTime().toString()
                })
                .findFirst()
                .orElse(new String[]{null, null, null});

        return new ParkingResource(
                entity.getId(),
                entity.getProfileId().profileIdAsPrimitive(),
                entity.getWidth(),
                entity.getLength(),
                entity.getHeight(),
                entity.getPrice(),
                entity.getPhone(),
                entity.getSpace().toString(),
                entity.getDescription(),
                entity.getLocation().getAddress(),
                entity.getLocation().getNumDirection(),
                entity.getLocation().getStreet(),
                entity.getLocation().getDistrict(),
                entity.getLocation().getCity(),
                entity.getLocation().getLatitude().toString(),
                entity.getLocation().getLongitude().toString(),
                scheduleData[0], // day
                scheduleData[1], // startTime
                scheduleData[2]  // endTime
        );
    }
}
