package com.ezpark.web_service.parkings.application.internal.queryservices;

import com.ezpark.web_service.parkings.domain.model.entities.Schedule;
import com.ezpark.web_service.parkings.domain.model.queries.GetAllScheduleQuery;
import com.ezpark.web_service.parkings.domain.model.queries.GetScheduleByDayStartTimeAndEndTimeQuery;
import com.ezpark.web_service.parkings.domain.model.queries.GetScheduleByIdQuery;
import com.ezpark.web_service.parkings.domain.services.ScheduleQueryService;
import com.ezpark.web_service.parkings.infrastructure.persistence.jpa.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleQueryServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> handle(GetAllScheduleQuery query) {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> handle(GetScheduleByDayStartTimeAndEndTimeQuery query) {
        return scheduleRepository.findByDayAndStartTimeAndEndTime(
                query.day(),
                query.startTime(),
                query.endTime()
        );
    }

    @Override
    public Optional<Schedule> handle(GetScheduleByIdQuery query) {
        return scheduleRepository.findById(query.scheduleId());
    }

}
