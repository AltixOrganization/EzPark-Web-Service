package com.ezpark.web_service.parkings.interfaces.rest.transform;

import com.ezpark.web_service.parkings.domain.model.entities.Schedule;
import com.ezpark.web_service.parkings.interfaces.rest.resources.ScheduleResource;

public class ScheduleResourceFromEntityAssembler {
    public static ScheduleResource toResourceFromEntity(Schedule entity) {
        return new ScheduleResource(
                entity.getId(),
                entity.getParking().getId(),
                entity.getDay(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getIsAvailable(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
