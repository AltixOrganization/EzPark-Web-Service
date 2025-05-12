package com.ezpark.web_service.parkings.application.internal.queryservices;

import com.ezpark.web_service.parkings.domain.model.entities.Location;
import com.ezpark.web_service.parkings.domain.model.queries.GetAllLocationsQuery;
import com.ezpark.web_service.parkings.domain.services.LocationQueryService;
import com.ezpark.web_service.parkings.infrastructure.persistence.jpa.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationQueryServiceImpl implements LocationQueryService {

    private final LocationRepository locationRepository;

    public LocationQueryServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> handle(GetAllLocationsQuery query) {
        return locationRepository.findAll();
    }
}
