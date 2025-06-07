package com.ezpark.web_service.payments.application.internal.commandservices;

import com.ezpark.web_service.payments.application.internal.outboundservices.acl.KafkaReservationContextFacade;
import com.ezpark.web_service.payments.domain.model.aggregates.Payment;
import com.ezpark.web_service.payments.domain.model.commands.CreatePaymentCommand;
import com.ezpark.web_service.payments.domain.model.exceptions.ExternalServiceCommunicationException;
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
    private final KafkaReservationContextFacade kafkaReservationContextFacade;

    @Transactional
    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        try {
            kafkaReservationContextFacade.approveReservation(command.reservationId());

        } catch (ReservationApprovalException e) {
            // 2. Manejar fallos de NEGOCIO: La reserva existe pero no se pudo aprobar.
            // Simplemente relanzamos la excepción para que una capa superior (ej. un ControllerAdvice)
            // la capture y devuelva una respuesta HTTP apropiada (ej. 400 o 409).
            throw e;

        } catch (Exception e) {
            throw new ExternalServiceCommunicationException("Fallo en la comunicación con el servicio de reservas durante la creación del pago.", e);
        }

        var payment = new Payment(command);
        var paymentSaved = paymentRepository.save(payment);

        return Optional.of(paymentSaved);
    }
}
