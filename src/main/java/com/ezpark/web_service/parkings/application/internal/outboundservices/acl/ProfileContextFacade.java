package com.ezpark.web_service.parkings.application.internal.outboundservices.acl;

import java.time.LocalDate;

public interface ProfileContextFacade {
    boolean checkProfileExistById(Long userId) throws Exception;
    //Long createProfile(Long userId, String firstName, String lastName, LocalDate birthDate) throws Exception;
}
