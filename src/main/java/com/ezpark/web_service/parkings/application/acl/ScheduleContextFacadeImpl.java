package com.ezpark.web_service.parkings.application.acl;

import com.ezpark.web_service.parkings.domain.model.commands.MarkScheduleAsUnavailable;
import com.ezpark.web_service.parkings.domain.model.entities.Schedule;
import com.ezpark.web_service.parkings.domain.model.queries.GetScheduleByIdQuery;
import com.ezpark.web_service.parkings.domain.services.ScheduleCommandService;
import com.ezpark.web_service.parkings.domain.services.ScheduleQueryService;
import com.ezpark.web_service.parkings.interfaces.acl.ScheduleContextFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ScheduleContextFacadeImpl implements ScheduleContextFacade {
    private final ScheduleQueryService scheduleQueryService;
    private  final ScheduleCommandService scheduleCommandService;

    public ScheduleContextFacadeImpl(ScheduleQueryService scheduleQueryService, ScheduleCommandService scheduleCommandService) {
        this.scheduleQueryService = scheduleQueryService;
        this.scheduleCommandService = scheduleCommandService;
    }

    @Override
    public boolean isScheduleAvailable(Long scheduleId) {
        var scheduleQuery = new GetScheduleByIdQuery(scheduleId);
        return scheduleQueryService.handle(scheduleQuery)
                .map(Schedule::getIsAvailable)
                .orElse(false);
    }

    @Override
    public boolean markScheduleAsUnavailable(Long scheduleId) {
        var command = new MarkScheduleAsUnavailable(scheduleId);
        return scheduleCommandService.handle(command)
                .map(schedule -> !schedule.getIsAvailable())
                .orElse(false);
    }
}
