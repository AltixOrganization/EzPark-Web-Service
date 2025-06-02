package com.ezpark.web_service.profiles.interfaces.rest.transformers;

import com.ezpark.web_service.profiles.domain.model.aggregates.Profile;
import com.ezpark.web_service.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getUserId().userIdAsPrimitive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
