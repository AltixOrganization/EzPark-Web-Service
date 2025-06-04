package com.ezpark.web_service.core.entities.unit.tests;

import com.ezpark.web_service.parkings.domain.model.commands.CreateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.commands.UpdateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.entities.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    private CreateScheduleCommand createCommand;
    private UpdateScheduleCommand updateCommand;

    @BeforeEach
    void setUp() {
        createCommand = new CreateScheduleCommand(
                1L,
                LocalDate.of(2024, 6, 5),
                LocalTime.of(8, 0),
                LocalTime.of(18, 0)
        );

        updateCommand = new UpdateScheduleCommand(
                1L,
                LocalDate.of(2024, 6, 6),
                LocalTime.of(9, 0),
                LocalTime.of(17, 0)
        );
    }

    @Test
    void testConstructorWithCommand() {
        Schedule schedule = new Schedule(createCommand);

        assertEquals(LocalDate.of(2024, 6, 5), schedule.getDay());
        assertEquals(LocalTime.of(8, 0), schedule.getStartTime());
        assertEquals(LocalTime.of(18, 0), schedule.getEndTime());
        assertTrue(schedule.getIsAvailable());
    }

    @Test
    void testUpdateSchedule() {
        Schedule schedule = new Schedule(createCommand);
        schedule.updatedSchedule(updateCommand);

        assertEquals(LocalDate.of(2024, 6, 6), schedule.getDay());
        assertEquals(LocalTime.of(9, 0), schedule.getStartTime());
        assertEquals(LocalTime.of(17, 0), schedule.getEndTime());
    }

    @Test
    void testSettersAndGetters() {
        Schedule schedule = new Schedule();
        LocalDate day = LocalDate.of(2024, 6, 7);
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(16, 0);

        schedule.setDay(day);
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        schedule.setIsAvailable(false);

        assertEquals(day, schedule.getDay());
        assertEquals(startTime, schedule.getStartTime());
        assertEquals(endTime, schedule.getEndTime());
        assertFalse(schedule.getIsAvailable());
    }
}