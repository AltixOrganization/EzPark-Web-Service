package com.ezpark.web_service.parkings.domain.services;

import com.ezpark.web_service.parkings.domain.model.entities.Schedule;
import com.ezpark.web_service.parkings.domain.model.queries.GetAllScheduleQuery;
import com.ezpark.web_service.parkings.domain.model.queries.GetScheduleByDayStartTimeAndEndTimeQuery;
import com.ezpark.web_service.parkings.domain.model.queries.GetScheduleByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ScheduleQueryService {
    List<Schedule> handle(GetAllScheduleQuery query);
    Optional<Schedule> handle(GetScheduleByDayStartTimeAndEndTimeQuery query);
    Optional<Schedule> handle(GetScheduleByIdQuery query);
}
