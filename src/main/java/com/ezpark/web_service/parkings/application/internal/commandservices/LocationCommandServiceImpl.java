package com.ezpark.web_service.parkings.application.internal.commandservices;

import com.ezpark.web_service.parkings.domain.model.commands.UpdateLocationCommand;
import com.ezpark.web_service.parkings.domain.model.entities.Location;
import com.ezpark.web_service.parkings.domain.services.LocationCommandService;
import com.ezpark.web_service.parkings.infrastructure.persistence.jpa.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationCommandServiceImpl implements LocationCommandService {

    private final LocationRepository locationRepository;

    public LocationCommandServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Optional<Location> handle(UpdateLocationCommand command) {
        var result = locationRepository.findById(command.locationId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Location does not exist");
        var locationToUpdate = result.get();
        try {
            var updatedLocation = locationRepository.save(locationToUpdate.updateLocation(command));
            return Optional.of(updatedLocation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating location: " + e.getMessage());
        }
    }
}
