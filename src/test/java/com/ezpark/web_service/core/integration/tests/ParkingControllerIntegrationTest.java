package com.ezpark.web_service.core.integration.tests;

import com.ezpark.web_service.parkings.domain.model.aggregates.Parking;
import com.ezpark.web_service.parkings.domain.model.commands.CreateParkingCommand;
import com.ezpark.web_service.parkings.domain.model.queries.GetAllParkingQuery;
import com.ezpark.web_service.parkings.domain.model.valueobjects.ProfileId;
import com.ezpark.web_service.parkings.domain.services.ParkingCommandService;
import com.ezpark.web_service.parkings.domain.services.ParkingQueryService;
import com.ezpark.web_service.parkings.interfaces.rest.ParkingController;
import com.ezpark.web_service.parkings.interfaces.rest.resources.CreateParkingResource;
import com.ezpark.web_service.parkings.interfaces.rest.resources.ParkingResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingControllerIntegrationTest {

    private ParkingCommandService parkingCommandService;
    private ParkingQueryService parkingQueryService;
    private ParkingController parkingController;

    @BeforeEach
    void setUp() {
        parkingCommandService = Mockito.mock(ParkingCommandService.class);
        parkingQueryService = Mockito.mock(ParkingQueryService.class);
        parkingController = new ParkingController(parkingCommandService, parkingQueryService);
    }

    @Test
    void testCreateParkingSuccess() {
        CreateParkingResource resource = new CreateParkingResource(
                1L, // profileId
                2.5, // width
                5.0, // length
                2.0, // height
                10.0, // price
                "987654321", // phone
                2, // space
                "Espacio techado", // description
                null // location (assuming it's not used in this test)
        );
        Parking parking = new Parking();

        Mockito.when(parkingCommandService.handle(ArgumentMatchers.any(CreateParkingCommand.class)))
                .thenReturn(Optional.of(parking));

        ResponseEntity<ParkingResource> response = parkingController.createParking(resource);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("987654321", response.getBody().phone());
        assertEquals("Espacio techado", response.getBody().description());
    }

    @Test
    void testCreateParkingBadRequest() {
        CreateParkingResource resource = new CreateParkingResource(
                null, // profileId (null to trigger validation error)
                2.5, // width
                5.0, // length
                2.0, // height
                10.0, // price
                "987654321", // phone
                2, // space
                "Espacio techado", // description
                null // location (assuming it's not used in this test)
        );

        Mockito.when(parkingCommandService.handle(ArgumentMatchers.any(CreateParkingCommand.class)))
                .thenReturn(Optional.empty());

        ResponseEntity<ParkingResource> response = parkingController.createParking(resource);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetAllParkingSuccess() {
        Parking parking = new Parking();

        Mockito.when(parkingQueryService.handle(ArgumentMatchers.any(GetAllParkingQuery.class)))
                .thenReturn(Collections.singletonList(parking));

        ResponseEntity<?> response = parkingController.getAllParking();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof java.util.List<?>);
        assertEquals(1, ((java.util.List<?>) response.getBody()).size());
    }
}