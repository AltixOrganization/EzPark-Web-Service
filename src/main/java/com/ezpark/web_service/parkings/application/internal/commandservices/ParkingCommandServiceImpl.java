package com.ezpark.web_service.parkings.application.internal.commandservices;

import com.ezpark.web_service.parkings.application.internal.outboundservices.acl.ExternalProfileService;
import com.ezpark.web_service.parkings.domain.model.aggregates.Parking;
import com.ezpark.web_service.parkings.domain.model.commands.CreateParkingCommand;
import com.ezpark.web_service.parkings.domain.model.commands.DeleteParkingCommand;
import com.ezpark.web_service.parkings.domain.model.commands.UpdateParkingCommand;
import com.ezpark.web_service.parkings.domain.services.ParkingCommandService;
import com.ezpark.web_service.parkings.infrastructure.persistence.jpa.repositories.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingCommandServiceImpl implements ParkingCommandService {
    private final ParkingRepository parkingRepository;
    private final ExternalProfileService externalProfileService;

    public ParkingCommandServiceImpl(ParkingRepository parkingRepository, ExternalProfileService externalProfileService) {
        this.parkingRepository = parkingRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public Optional<Parking> handle(CreateParkingCommand command) {
        if (!externalProfileService.checkProfileExistById(command.profileId())) {
            throw new IllegalArgumentException("Host not found");
        }
        var parking = new Parking(command);
        try {
            var response = parkingRepository.save(parking);
            return Optional.of(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Parking> handle(UpdateParkingCommand command) {
        var result = parkingRepository.findById(command.parkingId());

        if (result.isEmpty())
            throw new IllegalArgumentException("Parking does not exist");
        try {
            var parking = result.get();

            System.out.println("[DEBUG COMMAND DATA]" + command.address() + " " + command.numDirection());

            parking.updateParking(command);

            System.out.println("[DEBUG ENTITY]" + parking.getLocation().getAddress() + " " + parking.getLocation().getNumDirection());

            var updatedParking = parkingRepository.save(parking);
            return Optional.of(updatedParking);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void handle(DeleteParkingCommand command) {
        parkingRepository.deleteById(command.parkingId());
    }
}
