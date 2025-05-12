package com.ezpark.web_service.core.integration.tests;

import com.ezpark.web_service.profiles.domain.model.aggregates.Profile;
import com.ezpark.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.ezpark.web_service.profiles.domain.model.queries.GetProfileByIdQuery;
import com.ezpark.web_service.profiles.domain.services.ProfileCommandService;
import com.ezpark.web_service.profiles.domain.services.ProfileQueryService;
import com.ezpark.web_service.profiles.interfaces.rest.ProfileController;
import com.ezpark.web_service.profiles.interfaces.rest.resources.CreateProfileResource;
import com.ezpark.web_service.profiles.interfaces.rest.resources.ProfileResource;
import com.ezpark.web_service.profiles.interfaces.rest.transformers.CreateProfileCommandFromResourceAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfileControllerIntegrationTest {

    private ProfileCommandService profileCommandService;
    private ProfileQueryService profileQueryService;
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        profileQueryService = Mockito.mock(ProfileQueryService.class);
        profileCommandService = Mockito.mock(ProfileCommandService.class);
        profileController = new ProfileController(profileQueryService, profileCommandService);
    }

    @Test
    void testCreateProfileSuccess() {
        CreateProfileResource resource = new CreateProfileResource("John", "Doe", "john.doe@email.com", 1L);
        CreateProfileCommand command = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        Profile profile = new Profile(command);

        Mockito.when(profileCommandService.handle(ArgumentMatchers.any(CreateProfileCommand.class)))
                .thenReturn(Optional.of(profile));

        ResponseEntity<ProfileResource> response = profileController.createProfile(resource);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().name());
    }

    @Test
    void testGetProfileByIdSuccess() {
        Long profileId = 1L;
        Profile profile = new Profile(new CreateProfileCommand("John", "Doe", "john.doe@email.com", 1L));

        Mockito.when(profileQueryService.handle(ArgumentMatchers.any(GetProfileByIdQuery.class)))
                .thenReturn(Optional.of(profile));

        ResponseEntity<ProfileResource> response = profileController.getProfileById(profileId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().name());
    }
}