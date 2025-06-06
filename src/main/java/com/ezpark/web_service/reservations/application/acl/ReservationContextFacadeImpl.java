package com.ezpark.web_service.reservations.application.acl;

import com.ezpark.web_service.reservations.domain.model.commands.UpdateStatusCommand;
import com.ezpark.web_service.reservations.domain.model.queries.GetReservationByIdQuery;
import com.ezpark.web_service.reservations.domain.model.valueobject.Status;
import com.ezpark.web_service.reservations.domain.services.ReservationCommandService;
import com.ezpark.web_service.reservations.domain.services.ReservationQueryService;
import com.ezpark.web_service.reservations.interfaces.acl.ReservationContextFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReservationContextFacadeImpl implements ReservationContextFacade {
    private ReservationCommandService reservationCommandService;
    private ReservationQueryService reservationQueryService;

    @Override
    public boolean checkReservationExistById(Long reservationId) {
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        return reservationQueryService.handle(getReservationByIdQuery)
                .isPresent();
    }

    @Override
    public boolean markReservationAsApproved(Long reservationId) {
        var updateStatusCommand = new UpdateStatusCommand(reservationId, Status.Approved);
        var updatedReservation = reservationCommandService.handle(updateStatusCommand);
        return updatedReservation
                .map(reservation -> reservation.getStatus() == Status.Approved)
                .orElse(false);
    }
}
