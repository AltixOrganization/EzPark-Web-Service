package com.ezpark.web_service.parkings.domain.services;

import com.ezpark.web_service.parkings.domain.model.entities.Location;
import com.ezpark.web_service.parkings.domain.model.queries.GetAllLocationsQuery;

import java.util.List;

public interface LocationQueryService {
    List<Location> handle(GetAllLocationsQuery query);
}
