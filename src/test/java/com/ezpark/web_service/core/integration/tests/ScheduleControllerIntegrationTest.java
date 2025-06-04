package com.ezpark.web_service.core.integration.tests;

import com.ezpark.web_service.parkings.domain.model.aggregates.Parking;
import com.ezpark.web_service.parkings.domain.model.commands.CreateScheduleCommand;
import com.ezpark.web_service.parkings.domain.model.entities.Schedule;
import com.ezpark.web_service.parkings.domain.model.queries.GetAllScheduleQuery;
import com.ezpark.web_service.parkings.domain.services.ScheduleCommandService;
import com.ezpark.web_service.parkings.domain.services.ScheduleQueryService;
import com.ezpark.web_service.parkings.interfaces.rest.ScheduleController;
import com.ezpark.web_service.parkings.interfaces.rest.resources.CreateScheduleResource;
import com.ezpark.web_service.parkings.interfaces.rest.resources.ScheduleResource;
import com.ezpark.web_service.parkings.interfaces.rest.resources.UpdateScheduleResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScheduleControllerIntegrationTest {

    private ScheduleCommandService scheduleCommandService;
    private ScheduleQueryService scheduleQueryService;
    private ScheduleController scheduleController;

    @BeforeEach
    void setUp() {
        scheduleCommandService = Mockito.mock(ScheduleCommandService.class);
        scheduleQueryService = Mockito.mock(ScheduleQueryService.class);
        scheduleController = new ScheduleController(scheduleCommandService, scheduleQueryService);
    }

    @Test
    void testCreateScheduleSuccess() {
        // Arrange: Use LocalDate for the day
        CreateScheduleResource resource = new CreateScheduleResource(
                1L, LocalDate.of(2024, 6, 10), LocalTime.of(8, 0), LocalTime.of(18, 0)
        );

        Schedule mockSchedule = Mockito.mock(Schedule.class);
        ScheduleResource mockResource = Mockito.mock(ScheduleResource.class);

        Mockito.when(scheduleCommandService.handle((CreateScheduleCommand) ArgumentMatchers.any()))
                .thenReturn(Optional.of(mockSchedule));
        // If you have an assembler, mock it here

        // Act
        ResponseEntity<ScheduleResource> response = scheduleController.createSchedule(resource);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testCreateScheduleBadRequest() {
        CreateScheduleResource resource = new CreateScheduleResource(
                1L, LocalDate.of(2024, 6, 10), LocalTime.of(8, 0), LocalTime.of(18, 0)
        );
        Mockito.when(scheduleCommandService.handle((CreateScheduleCommand) ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        ResponseEntity<ScheduleResource> response = scheduleController.createSchedule(resource);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetAllSchedulesSuccess() {
        Schedule mockSchedule = Mockito.mock(Schedule.class);
        Mockito.when(scheduleQueryService.handle((GetAllScheduleQuery) ArgumentMatchers.any()))
                .thenReturn(Collections.singletonList(mockSchedule));

        ResponseEntity<java.util.List<ScheduleResource>> response = scheduleController.getAllSchedule();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testUpdateScheduleSuccess() {
        Long scheduleId = 1L;
        UpdateScheduleResource resource = new UpdateScheduleResource(
                LocalDate.of(2024, 6, 11), LocalTime.of(9, 0), LocalTime.of(17, 0)
        );
        Schedule mockSchedule = Mockito.mock(Schedule.class);
        ScheduleResource mockResource = Mockito.mock(ScheduleResource.class);

        Mockito.when(scheduleCommandService.handle((CreateScheduleCommand) ArgumentMatchers.any()))
                .thenReturn(Optional.of(mockSchedule));

        ResponseEntity<ScheduleResource> response = scheduleController.updateSchedule(scheduleId, resource);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateScheduleNotFound() {
        Long scheduleId = 1L;
        UpdateScheduleResource resource = new UpdateScheduleResource(
                LocalDate.of(2024, 6, 11), LocalTime.of(9, 0), LocalTime.of(17, 0)
        );
        Mockito.when(scheduleCommandService.handle((CreateScheduleCommand) ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        ResponseEntity<ScheduleResource> response = scheduleController.updateSchedule(scheduleId, resource);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}