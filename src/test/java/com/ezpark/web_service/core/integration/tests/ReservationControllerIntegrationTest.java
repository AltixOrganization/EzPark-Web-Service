package com.ezpark.web_service.core.integration.tests;

import com.ezpark.web_service.reservations.domain.model.aggregates.Reservation;
import com.ezpark.web_service.reservations.domain.model.commands.CreateReservationCommand;
import com.ezpark.web_service.reservations.domain.model.commands.UpdateReservationCommand;
import com.ezpark.web_service.reservations.domain.model.commands.UpdateStatusCommand;
import com.ezpark.web_service.reservations.domain.model.queries.*;
import com.ezpark.web_service.reservations.domain.model.valueobject.*;
import com.ezpark.web_service.reservations.domain.services.ReservationCommandService;
import com.ezpark.web_service.reservations.domain.services.ReservationQueryService;
import com.ezpark.web_service.reservations.interfaces.rest.ReservationController;
import com.ezpark.web_service.reservations.interfaces.rest.resources.CreateReservationResource;
import com.ezpark.web_service.reservations.interfaces.rest.resources.ReservationResource;
import com.ezpark.web_service.reservations.interfaces.rest.resources.UpdateReservationResource;
import com.ezpark.web_service.reservations.interfaces.rest.resources.UpdateStatusResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationControllerIntegrationTest {
    private ReservationCommandService reservationCommandService;
    private ReservationQueryService reservationQueryService;
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        reservationCommandService = Mockito.mock(ReservationCommandService.class);
        reservationQueryService = Mockito.mock(ReservationQueryService.class);
        reservationController = new ReservationController(reservationCommandService, reservationQueryService);
    }

    @Test
    void testCreateReservationSuccess() {
        CreateReservationResource resource = new CreateReservationResource(
                2, 20.0, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
                1L, 2L, 3L, 4L, 5L
        );
        Reservation mockReservation = Mockito.mock(Reservation.class);
        ReservationResource mockResource = Mockito.mock(ReservationResource.class);

        Mockito.when(reservationCommandService.handle(ArgumentMatchers.any(CreateReservationCommand.class)))
                .thenReturn(Optional.of(mockReservation));
        // Simulate assembler
        // Mockito.when(ReservationResourceFromEntityAssembler.toResourceFromEntity(mockReservation)).thenReturn(mockResource);

        ResponseEntity<ReservationResource> response = reservationController.createReservation(resource);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testCreateReservationBadRequest() {
        CreateReservationResource resource = new CreateReservationResource(
                2, 20.0, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0),
                1L, 2L, 3L, 4L, 5L
        );
        Mockito.when(reservationCommandService.handle(ArgumentMatchers.any(CreateReservationCommand.class)))
                .thenReturn(Optional.empty());

        ResponseEntity<ReservationResource> response = reservationController.createReservation(resource);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetAllReservationsSuccess() {
        Reservation mockReservation = Mockito.mock(Reservation.class);
        Mockito.when(reservationQueryService.handle(ArgumentMatchers.any(GetAllReservationsQuery.class)))
                .thenReturn(Collections.singletonList(mockReservation));

        ResponseEntity<List<ReservationResource>> response = reservationController.getAllReservations();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}