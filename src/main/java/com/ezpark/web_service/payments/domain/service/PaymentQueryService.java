package com.ezpark.web_service.payments.domain.service;

import com.ezpark.web_service.payments.domain.model.aggregates.Payment;
import com.ezpark.web_service.payments.domain.model.queries.GetAllPaymentsQuery;
import com.ezpark.web_service.payments.domain.model.queries.GetPaymentByReservationId;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByReservationId query);
    List<Payment> handle(GetAllPaymentsQuery query);
}
