package com.ezpark.web_service.core.entities.unit.tests;

import com.ezpark.web_service.parkings.domain.model.aggregates.Parking;
import com.ezpark.web_service.parkings.domain.model.commands.CreateParkingCommand;
import com.ezpark.web_service.parkings.domain.model.entities.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ParkingTest {

    private Parking parking;
    private Location mockLocation;
    private CreateParkingCommand mockCommand;

    @BeforeEach
    void setUp() {
        // Arrange: Crear mocks y objetos necesarios
        mockLocation = mock(Location.class);
        mockCommand = new CreateParkingCommand(
                1L, // profileId
                2.5, // width
                5.0, // length
                3.0, // height
                10.0, // price
                "123456789", // phone
                1, // space
                "Test Description", // description
                null // location (can be null for this test)
        );

        parking = new Parking();
    }

    @Test
    void testConstructorWithCommand() {
        // Arrange
        CreateParkingCommand command = mockCommand;

        // Act
        Parking parking = new Parking(command);

        // Assert
        assertNotNull(parking.getLocation());
        assertEquals(1L, parking.getProfileId().profileIdAsPrimitive());
        assertEquals(2.5, parking.getWidth());
        assertEquals(5.0, parking.getLength());
        assertEquals(3.0, parking.getHeight());
        assertEquals(10.0, parking.getPrice());
        assertEquals("123456789", parking.getPhone());
        assertEquals(1, parking.getSpace());
        assertEquals("Test Description", parking.getDescription());
        assertEquals("Test Address", parking.getLocation().getAddress());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Parking parking = new Parking();

        // Act
        parking.setWidth(2.5);
        parking.setLength(5.0);
        parking.setHeight(3.0);
        parking.setPrice(10.0);
        parking.setPhone("123456789");
        parking.setSpace(1);
        parking.setDescription("Test Description");

        // Assert
        assertEquals(2.5, parking.getWidth());
        assertEquals(5.0, parking.getLength());
        assertEquals(3.0, parking.getHeight());
        assertEquals(10.0, parking.getPrice());
        assertEquals("123456789", parking.getPhone());
        assertEquals(1, parking.getSpace());
        assertEquals("Test Description", parking.getDescription());
    }

    @Test
    void testSetAndGetLocation() {
        // Arrange
        when(mockLocation.getAddress()).thenReturn("Mock Address");

        // Act
        parking.setLocation(mockLocation);

        // Assert
        assertNotNull(parking.getLocation());
        assertEquals("Mock Address", parking.getLocation().getAddress());
        verify(mockLocation, times(1)).getAddress();
    }
}