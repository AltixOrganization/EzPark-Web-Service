package com.ezpark.web_service.profiles.application.acl;

import com.ezpark.web_service.profiles.domain.model.queries.GetProfileByIdQuery;
import com.ezpark.web_service.profiles.domain.services.ProfileQueryService;
import com.ezpark.web_service.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;


@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacadeImpl(ProfileQueryService profileQueryService) {
        this.profileQueryService = profileQueryService;
    }

    @Override
    public boolean checkProfileExistById(Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        return profile.isPresent();
    }
}
