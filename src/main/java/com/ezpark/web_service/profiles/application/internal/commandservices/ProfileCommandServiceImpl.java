package com.ezpark.web_service.profiles.application.internal.commandservices;


import com.ezpark.web_service.profiles.application.internal.outboundservices.acl.ExternalUserService;
import com.ezpark.web_service.profiles.domain.model.aggregates.Profile;
import com.ezpark.web_service.profiles.domain.model.commands.CreateProfileCommand;
import com.ezpark.web_service.profiles.domain.model.commands.DeleteProfileCommand;
import com.ezpark.web_service.profiles.domain.model.commands.UpdateProfileCommand;
import com.ezpark.web_service.profiles.domain.services.ProfileCommandService;
import com.ezpark.web_service.profiles.infrastructure.repositories.jpa.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;
    private final ExternalUserService externalUserService;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, ExternalUserService externalUserService) {
        this.profileRepository = profileRepository;
        this.externalUserService = externalUserService;
    }


    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        if(!externalUserService.checkUserExistsByUserId(command.userId()))
        {
            throw new IllegalArgumentException("User with ID " + command.userId() + " does not exist.");
        }
        Profile user = new Profile(command);
        try {
            var response = profileRepository.save(user);

            return Optional.of(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Profile> handle(UpdateProfileCommand command) {
        var result = profileRepository.findById(command.profileId());
        if (result.isEmpty())
            throw new IllegalArgumentException("User does not exist");
        var userToUpdate = result.get();
        try{
            var updatedUser= profileRepository.save(userToUpdate.updatedProfile(command));
            return Optional.of(updatedUser);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating user: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteProfileCommand command){
        if (!profileRepository.existsById(command.profileId())) throw new IllegalArgumentException("User does not exist");
        profileRepository.deleteById(command.profileId());
        System.out.println("User Delete");
    }

}
