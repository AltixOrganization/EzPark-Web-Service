package com.ezpark.web_service.reservations.application.internal.commandservices;

import com.ezpark.web_service.reservations.application.internal.outboundservices.acl.ExternalParkingService;
import com.ezpark.web_service.reservations.application.internal.outboundservices.acl.ExternalProfileService;
import com.ezpark.web_service.reservations.application.internal.outboundservices.acl.ExternalScheduleService;
import com.ezpark.web_service.reservations.application.internal.outboundservices.acl.VehiclesContextFacade;
import com.ezpark.web_service.reservations.domain.model.aggregates.Reservation;
import com.ezpark.web_service.reservations.domain.model.commands.CreateReservationCommand;
import com.ezpark.web_service.reservations.domain.model.commands.UpdateReservationCommand;
import com.ezpark.web_service.reservations.domain.model.commands.UpdateStatusCommand;
import com.ezpark.web_service.reservations.domain.model.exceptions.*;
import com.ezpark.web_service.reservations.domain.model.valueobject.ParkingId;
import com.ezpark.web_service.reservations.domain.model.valueobject.Status;
import com.ezpark.web_service.reservations.domain.services.ReservationCommandService;
import com.ezpark.web_service.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {
    private final ReservationRepository reservationRepository;
    private final ExternalProfileService externalProfileService;
    private final VehiclesContextFacade vehiclesContextFacade;
    private final ExternalParkingService externalParkingService;
    private final ExternalScheduleService externalScheduleService;

    @Transactional
    @Override
    public Optional<Reservation> handle(CreateReservationCommand command) {
        try {
            if (!externalProfileService.checkProfileExistById(command.guestId()) || !externalProfileService.checkProfileExistById(command.hostId())) {
                throw new ProfileNotFoundException();
            }
            if (!vehiclesContextFacade.checkVehicleExistsById(command.vehicleId())) {
                throw new VehicleNotFoundException();
            }
            if (!externalParkingService.checkParkingExistById(command.parkingId())) {
                throw new ParkingNotFoundException();
            }
            if (!externalScheduleService.isScheduleAvailable(command.scheduleId())) {
                throw new ScheduleConflictException();
            }
        } catch (Exception e) {
            throw new RuntimeException("Fallo al validar pre-condiciones con servicios externos.", e);
        }

        List<Status> blockedStatuses = List.of(Status.Approved, Status.InProgress, Status.Completed);
        if (reservationRepository.existsByOverlapWithStatus(
                new ParkingId(command.parkingId()),
                command.reservationDate(),
                command.startTime(),
                command.endTime(),
                blockedStatuses)) {
            throw new ReservationOverlapException();
        }

        var reservation = new Reservation(command);
        reservation.setStatus(Status.Pending);

        var savedReservation = reservationRepository.save(reservation);
        // Aquí podrías publicar un evento de dominio "ReservationCreatedEvent"
        // kafkaEventPublisher.publish(..., new ReservationCreatedEvent(...));
        return Optional.of(savedReservation);
    }

    @Transactional
    @Override
    public Optional<Reservation> handle(UpdateReservationCommand command) {
        var result = reservationRepository.findById(command.reservationId());
        if (result.isEmpty())
            throw new ReservationNotFoundException();
        Long currentScheduleId = result.get().getScheduleId().scheduleId();
        if (!command.scheduleId().equals(currentScheduleId) &&
                !externalScheduleService.isScheduleAvailable(command.scheduleId())) {
            throw new ScheduleConflictException();
        }

        List<Status> blockedStatuses = List.of(Status.Approved, Status.InProgress, Status.Completed);

        if (reservationRepository.existsByOverlapWithStatus(
                new ParkingId(result.get().getParkingId().parkingId()),
                command.reservationDate(),
                command.startTime(),
                command.endTime(),
                blockedStatuses)) {
            throw new ReservationOverlapException();
        }

        var reservationToUpdate = result.get();
        try{
            var updatedReservation= reservationRepository.save(reservationToUpdate.updatedReservation(command));
            return Optional.of(updatedReservation);
        }catch (Exception e){
            throw new ReservationUpdateException();
        }
    }

    @Override
    @Transactional
    public Optional<Reservation> handle(UpdateStatusCommand command) {
        var result = reservationRepository.findById(command.reservationId());
        if (result.isEmpty())
            throw new ReservationNotFoundException();
        var statusToUpdate = result.get();
        try {
            if (command.status() == Status.Approved) {
                boolean updated = externalScheduleService.markScheduleAsUnavailable(
                        statusToUpdate.getScheduleId().scheduleId());

                if (!updated) {
                    throw new ScheduleUpdateException();
                }
            }
            var updatedStatus = reservationRepository.save(statusToUpdate.updatedStatus(command));
            return Optional.of(updatedStatus);
        }catch (Exception e){
            throw new ReservationUpdateException();
        }
    }
}
