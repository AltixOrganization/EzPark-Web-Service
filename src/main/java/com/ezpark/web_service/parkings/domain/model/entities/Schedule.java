package com.ezpark.web_service.parkings.domain.model.entities;

import com.ezpark.web_service.parkings.domain.model.aggregates.Parking;
import com.ezpark.web_service.parkings.domain.model.commands.CreateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.commands.UpdateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.valueobjects.WeekDay;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parking_id", nullable = false)
    @JsonBackReference
    private Parking parking;

    @Enumerated(EnumType.STRING)
    private WeekDay day;
    private LocalTime startTime;
    private LocalTime endTime;

    public Schedule(CreateScheduleCommand command) {
        this.day = WeekDay.valueOf(command.day());
        this.startTime = command.startTime();
        this.endTime = command.endTime();
    }

    public Schedule updatedSchedule(UpdateScheduleCommand command) {
        this.day = WeekDay.valueOf(command.day());
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        return this;
    }
}