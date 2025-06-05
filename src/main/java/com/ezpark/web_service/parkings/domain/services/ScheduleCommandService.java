package com.ezpark.web_service.parkings.domain.services;

import com.ezpark.web_service.parkings.domain.model.commands.CreateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.commands.DeleteScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.commands.MarkScheduleAsUnavailable;
import com.ezpark.web_service.parkings.domain.model.commands.UpdateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.entities.Schedule;

import java.util.Optional;

public interface ScheduleCommandService {
    Optional<Schedule> handle(CreateScheduleCommand command);
    Optional<Schedule> handle(UpdateScheduleCommand command);
    Optional<Schedule> handle(MarkScheduleAsUnavailable command);
    void handle(DeleteScheduleCommand command);
}
