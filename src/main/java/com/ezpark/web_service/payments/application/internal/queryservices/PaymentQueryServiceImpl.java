package com.ezpark.web_service.payments.application.internal.queryservices;

import com.ezpark.web_service.payments.domain.model.aggregates.Payment;
import com.ezpark.web_service.payments.domain.model.queries.GetAllPaymentsQuery;
import com.ezpark.web_service.payments.domain.model.queries.GetPaymentByReservationId;
import com.ezpark.web_service.payments.domain.model.valueobject.ReservationId;
import com.ezpark.web_service.payments.domain.service.PaymentQueryService;
import com.ezpark.web_service.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    @Override
    public Optional<Payment> handle(GetPaymentByReservationId query) {
        return paymentRepository.findByReservationId(new ReservationId(query.reservationId()));
    }

    @Override
    public List<Payment> handle(GetAllPaymentsQuery query) {
        return paymentRepository.findAll();
    }
}
