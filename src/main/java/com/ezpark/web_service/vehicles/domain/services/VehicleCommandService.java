package com.ezpark.web_service.vehicles.domain.services;

import com.ezpark.web_service.vehicles.domain.model.aggregates.Vehicle;
import com.ezpark.web_service.vehicles.domain.model.commands.CreateVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.DeleteVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.UpdateVehicleCommand;

import java.util.Optional;

public interface VehicleCommandService {
    Optional<Vehicle> handle(CreateVehicleCommand command) throws Exception;
    Optional<Vehicle> handle(UpdateVehicleCommand command);
    void handle(DeleteVehicleCommand command);
}