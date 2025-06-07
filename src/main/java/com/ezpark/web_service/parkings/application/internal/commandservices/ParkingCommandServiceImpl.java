package com.ezpark.web_service.parkings.application.internal.commandservices;

import com.ezpark.web_service.parkings.application.internal.outboundservices.acl.KafkaProfileContextFacade;
import com.ezpark.web_service.parkings.application.internal.outboundservices.acl.ProfileContextFacade;
import com.ezpark.web_service.parkings.domain.model.aggregates.Parking;
import com.ezpark.web_service.parkings.domain.model.commands.CreateParkingCommand;
import com.ezpark.web_service.parkings.domain.model.commands.DeleteParkingCommand;
import com.ezpark.web_service.parkings.domain.model.commands.UpdateParkingCommand;
import com.ezpark.web_service.parkings.domain.model.exceptions.*;
import com.ezpark.web_service.parkings.domain.services.ParkingCommandService;
import com.ezpark.web_service.parkings.infrastructure.persistence.jpa.repositories.ParkingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ParkingCommandServiceImpl implements ParkingCommandService {

    private final ParkingRepository parkingRepository;
    private final ProfileContextFacade profileContextFacade ;

    @Override
    public Optional<Parking> handle(CreateParkingCommand command) {
        try {
            if (!profileContextFacade.checkProfileExistById(command.profileId())) {
                throw new ProfileNotFoundException();
            }
            var parking = new Parking(command);

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
            throw new ParkingNotFoundException();
        try {
            var parking = result.get();

            System.out.println("[DEBUG COMMAND DATA]" + command.location().address() + " " + command.location().numDirection());

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
