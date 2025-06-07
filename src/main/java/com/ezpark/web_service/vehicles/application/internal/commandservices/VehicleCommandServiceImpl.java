package com.ezpark.web_service.vehicles.application.internal.commandservices;

import com.ezpark.web_service.vehicles.application.internal.outboundservices.acl.ExternalProfileService;
import com.ezpark.web_service.vehicles.domain.model.aggregates.Vehicle;
import com.ezpark.web_service.vehicles.domain.model.commands.CreateVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.DeleteVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.commands.UpdateVehicleCommand;
import com.ezpark.web_service.vehicles.domain.model.entities.Model;
import com.ezpark.web_service.vehicles.domain.model.exceptions.*;
import com.ezpark.web_service.vehicles.domain.services.VehicleCommandService;
import com.ezpark.web_service.vehicles.infrastructure.persistence.jpa.repositories.ModelRepository;
import com.ezpark.web_service.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final VehicleRepository vehicleRepository;
    private final ExternalProfileService externalProfileService;
    private final ModelRepository modelRepository;


    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {
        if (!externalProfileService.checkProfileExistById(command.profileId())) {
            throw new ProfileNotFoundException();
        }
        if (vehicleRepository.existsByLicensePlate(command.licensePlate())) {
            throw new VehicleLicensePlateConflictException();
        }
        Model model = modelRepository.findById(command.modelId())
                .orElseThrow(ModelNotFoundException::new);
        Vehicle vehicle = new Vehicle(command, model);
        var response = vehicleRepository.save(vehicle);
        return Optional.of(response);
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var result = vehicleRepository.findById(command.vehicleId());
        if (result.isEmpty())
            throw new VehicleNotFoundException();
        Model model = modelRepository.findById(command.modelId())
                .orElseThrow(ModelNotFoundException::new);
        var vehicleToUpdate = result.get();
        try{
            var updatedVehicle= vehicleRepository.save(vehicleToUpdate.updatedVehicle(command, model));
            return Optional.of(updatedVehicle);
        }catch (Exception e){
            throw new VehicleUpdateException();
        }
    }

    @Override
    public void handle(DeleteVehicleCommand command){
        if (!vehicleRepository.existsById(command.vehicleId())) throw new VehicleNotFoundException();
        vehicleRepository.deleteById(command.vehicleId());
        System.out.println("Vehicle Delete");
    }


}