package com.ezpark.web_service.core.entities.unit.tests;

import com.ezpark.web_service.profiles.domain.model.aggregates.Profile;
import com.ezpark.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.ezpark.web_service.profiles.domain.model.commands.UpdateProfileCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    private CreateProfileCommand createCommand;
    private UpdateProfileCommand updateCommand;

    @BeforeEach
    void setUp() {
        createCommand = new CreateProfileCommand("John", "Doe", LocalDate.of(2000, 1, 1), 1L);
        updateCommand = new UpdateProfileCommand(1L, "Jane", "Smith", LocalDate.of(1995, 5, 5));
    }

    @Test
    void testConstructorWithCreateCommand() {
        Profile profile = new Profile(createCommand);

        assertEquals("John", profile.getFirstName());
        assertEquals("Doe", profile.getLastName());
        assertEquals(LocalDate.of(2000, 1, 1), profile.getBirthDate());
        assertNotNull(profile.getUserId());
        assertEquals(1L, profile.getUserId().userIdAsPrimitive());
    }

    @Test
    void testUpdateProfile() {
        Profile profile = new Profile(createCommand);
        profile.updatedProfile(updateCommand);

        assertEquals("Jane", profile.getFirstName());
        assertEquals("Smith", profile.getLastName());
        assertEquals(LocalDate.of(1995, 5, 5), profile.getBirthDate());
    }

    @Test
    void testSettersAndGetters() {
        Profile profile = new Profile();
        profile.setFirstName("Alice");
        profile.setLastName("Brown");
        profile.setBirthDate(LocalDate.of(1990, 2, 2));

        assertEquals("Alice", profile.getFirstName());
        assertEquals("Brown", profile.getLastName());
        assertEquals(LocalDate.of(1990, 2, 2), profile.getBirthDate());
    }
}