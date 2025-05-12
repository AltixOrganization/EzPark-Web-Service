package com.ezpark.web_service.profiles.domain.services;

import com.ezpark.web_service.profiles.domain.model.aggregates.Profile;
import com.ezpark.web_service.profiles.domain.model.queries.GetAllProfilesQuery;
import com.ezpark.web_service.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);
}
