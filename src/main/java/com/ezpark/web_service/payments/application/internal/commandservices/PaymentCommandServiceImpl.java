package com.ezpark.web_service.payments.application.internal.commandservices;

import com.ezpark.web_service.payments.application.internal.outboundservices.acl.ExternalReservationService;
import com.ezpark.web_service.payments.domain.model.aggregates.Payment;
import com.ezpark.web_service.payments.domain.model.commands.CreatePaymentCommand;
import com.ezpark.web_service.payments.domain.model.exceptions.ReservationApprovalException;
import com.ezpark.web_service.payments.domain.model.exceptions.ReservationNotFoundException;
import com.ezpark.web_service.payments.domain.service.PaymentCommandService;
import com.ezpark.web_service.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private PaymentRepository paymentRepository;
    private final ExternalReservationService externalReservationService;

    @Transactional
    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        var payment = new Payment(command);
        if (!externalReservationService.checkReservationExistById(command.reservationId())) {
            throw new ReservationNotFoundException();
        }
        boolean reservationAsApproved = externalReservationService.markReservationAsApproved(command.reservationId());
        if (!reservationAsApproved) {
            throw new ReservationApprovalException();
        }
        var paymentSaved = paymentRepository.save(payment);
        return Optional.of(paymentSaved);
    }
}
