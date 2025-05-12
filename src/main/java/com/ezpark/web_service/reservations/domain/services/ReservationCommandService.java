package com.ezpark.web_service.reservations.domain.services;

import com.ezpark.web_service.reservations.domain.model.aggregates.Reservation;
import com.ezpark.web_service.reservations.domain.model.commands.CreateReservationCommand;
import com.ezpark.web_service.reservations.domain.model.commands.UpdateReservationCommand;
import com.ezpark.web_service.reservations.domain.model.commands.UpdateStatusCommand;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ReservationCommandService {
    Optional<Reservation> handle(CreateReservationCommand command, MultipartFile file);
    Optional<Reservation> handle(UpdateReservationCommand command);
    Optional<Reservation> handle(UpdateStatusCommand command);
}
