package com.ezpark.web_service.parkings.application.internal.outboundservices.acl;

import com.ezpark.web_service.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service("parkingExternalProfileService")
public class ExternalProfileService {
    private final ProfilesContextFacade userContextFacade;

    public ExternalProfileService(ProfilesContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }
    public boolean checkProfileExistById(Long userId) {
        return userContextFacade.checkProfileExistById(userId);
    }
}
