package com.ezpark.web_service.parkings.interfaces.rest.resources;

import java.time.LocalTime;

public record CreateScheduleResource(
        Long parkingId,
        String day,
        LocalTime startTime,
        LocalTime endTime
) {
}
