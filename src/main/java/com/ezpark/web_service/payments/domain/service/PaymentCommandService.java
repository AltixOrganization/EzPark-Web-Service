package com.ezpark.web_service.payments.domain.service;

import com.ezpark.web_service.payments.domain.model.aggregates.Payment;
import com.ezpark.web_service.payments.domain.model.commands.CreatePaymentCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle(CreatePaymentCommand command);
}
