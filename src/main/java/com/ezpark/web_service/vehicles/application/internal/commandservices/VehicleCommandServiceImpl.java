package com.ezpark.web_service.vehicles.application.internal.commandservices;

import com.ezpark.web_service.vehicles.application.internal.outboundservices.acl.ExternalProfileService;
import com.ezpark.web_service.vehicles.domain.model.aggregates.Vehicle;
import com.ezpark.web_service.vehicles.domain.model.commands.CreateVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.DeleteVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.UpdateVehicleCommand;
import com.ezpark.web_service.vehicles.domain.services.VehicleCommandService;
import com.ezpark.web_service.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final VehicleRepository vehicleRepository;
    private final ExternalProfileService externalProfileService;

    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository, ExternalProfileService externalProfileService) {
        this.vehicleRepository = vehicleRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {
        if (!externalProfileService.checkProfileExistById(command.profileId())) {
            throw new IllegalArgumentException("User not found");
        }
        if (vehicleRepository.existsByLicensePlate(command.licensePlate())) {
            throw new IllegalArgumentException("A vehicle with the license plate " + command.licensePlate() + " already exists.");
        }
        Vehicle vehicle = new Vehicle(command);
        var response = vehicleRepository.save(vehicle);
        return Optional.of(response);
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var result = vehicleRepository.findById(command.vehicleId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Vehicle does not exist");
        var vehicleToUpdate = result.get();
        try{
            var updatedVehicle= vehicleRepository.save(vehicleToUpdate.updatedVehicle(command));
            return Optional.of(updatedVehicle);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating vehicle: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteVehicleCommand command){
        if (!vehicleRepository.existsById(command.vehicleId())) throw new IllegalArgumentException("Vehicle does not exist");
        vehicleRepository.deleteById(command.vehicleId());
        System.out.println("Vehicle Delete");
    }


}