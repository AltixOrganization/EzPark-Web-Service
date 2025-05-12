package com.ezpark.web_service.parkings.domain.model.queries;


import com.ezpark.web_service.parkings.domain.model.valueobjects.WeekDay;

import java.time.LocalTime;

public record CheckScheduleEnclosingTimeRangeQuery(
        WeekDay day,
        LocalTime startTime,
        LocalTime endTime
) {
}
