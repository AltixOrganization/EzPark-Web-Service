package com.ezpark.web_service.parkings.application.internal.commandservices;

import com.ezpark.web_service.parkings.domain.model.commands.CreateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.commands.UpdateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.entities.Schedule;
import com.ezpark.web_service.parkings.domain.services.ScheduleCommandService;
import com.ezpark.web_service.parkings.infrastructure.persistence.jpa.repositories.ParkingRepository;
import com.ezpark.web_service.parkings.infrastructure.persistence.jpa.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService {
    private final ScheduleRepository scheduleRepository;
    private final ParkingRepository parkingRepository;

    public ScheduleCommandServiceImpl(ScheduleRepository scheduleRepository, ParkingRepository parkingRepository) {
        this.scheduleRepository = scheduleRepository;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public Optional<Schedule> handle(CreateScheduleCommand command) {
        Schedule schedule = new Schedule(command);
        try {
            var parking = parkingRepository.findById(command.parkingId());

            parking.map((p) -> {
                schedule.setParking(p);
                return p;
            }).orElseThrow(() -> new IllegalArgumentException("Parking not founded"));

            var response = scheduleRepository.save(schedule);
            return Optional.of(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Schedule> handle(UpdateScheduleCommand command) {
        var result = scheduleRepository.findById(command.scheduleId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Schedule does not exist");
        var scheduleToUpdate = result.get();
        try {
            var updatedSchedule = scheduleRepository.save(scheduleToUpdate.updatedSchedule(command));
            return Optional.of(updatedSchedule);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating schedule: " + e.getMessage());
        }
    }
}
