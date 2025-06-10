package com.ezpark.web_service.reservations.application.internal.outboundservices.acl;

public interface ProfileContextFacade {
    boolean checkProfileExistById(Long userId) throws Exception;
    //Long createProfile(Long userId, String firstName, String lastName, LocalDate birthDate) throws Exception;
}
