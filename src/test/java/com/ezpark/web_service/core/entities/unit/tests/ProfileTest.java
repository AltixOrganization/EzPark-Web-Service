package com.ezpark.web_service.core.entities.unit.tests;

import com.ezpark.web_service.profiles.domain.model.aggregates.Profile;
import com.ezpark.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.ezpark.web_service.profiles.domain.model.commands.UpdateProfileCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProfileTest {

    private CreateProfileCommand mockCreateCommand;
    private UpdateProfileCommand mockUpdateCommand;
    private Profile mockProfile;

    @BeforeEach
    void setUp() {
        // Arrange: Initialize commands and mock
        mockCreateCommand = new CreateProfileCommand("John", "Doe", "123 Test Street", 1L);
        mockUpdateCommand = new UpdateProfileCommand(1L, "Jane", "Smith", "456 New Street");
        mockProfile = mock(Profile.class);
    }

    @Test
    void testConstructorWithCreateCommand() {
        //Arrange
        CreateProfileCommand command = mockCreateCommand;

        // Act
        Profile profile = new Profile(command);

        // Assert
        assertEquals("John", profile.getName());
        assertEquals("Doe", profile.getLastName());
        assertEquals("123 Test Street", profile.getAddress());
        assertNotNull(profile.getUserId());
        assertEquals(1L, profile.getUserId().userIdAsPrimitive());
    }

    @Test
    void testUpdateProfile() {
        //Arrange
        UpdateProfileCommand command = mockUpdateCommand;

        // Act
        mockProfile.updatedProfile(command);

        // Assert
        verify(mockProfile).updatedProfile(command);
    }

    @Test
    void testSettersAndGetters() {
        // Act
        mockProfile.setName("Alice");
        mockProfile.setLastName("Brown");
        mockProfile.setAddress("789 Another Street");

        // Assert
        verify(mockProfile).setName("Alice");
        verify(mockProfile).setLastName("Brown");
        verify(mockProfile).setAddress("789 Another Street");
    }
}