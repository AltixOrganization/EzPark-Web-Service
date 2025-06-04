package com.ezpark.web_service.reservations.application.internal.outboundservices.acl;

import com.ezpark.web_service.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

@Service("reservationExternalProfileService")
public class ExternalProfileService {
    private final ProfileContextFacade profilesContextFacade;

    public ExternalProfileService(ProfileContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public boolean checkProfileExistById(Long profileId) {
        return profilesContextFacade.checkProfileExistById(profileId);
    }
}