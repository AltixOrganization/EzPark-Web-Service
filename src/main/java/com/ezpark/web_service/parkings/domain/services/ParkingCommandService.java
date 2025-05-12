package com.ezpark.web_service.parkings.domain.services;

import com.ezpark.web_service.parkings.domain.model.aggregates.Parking;
import com.ezpark.web_service.parkings.domain.model.commands.CreateParkingCommand;
import com.ezpark.web_service.parkings.domain.model.commands.DeleteParkingCommand;
import com.ezpark.web_service.parkings.domain.model.commands.UpdateParkingCommand;

import java.util.Optional;

public interface ParkingCommandService {
    Optional<Parking> handle(CreateParkingCommand command);
    Optional<Parking> handle(UpdateParkingCommand command);
    void handle(DeleteParkingCommand command);
}
