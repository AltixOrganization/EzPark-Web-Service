package com.ezpark.web_service.payments.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentErrorCatalog {
    RESERVATION_NOT_FOUND("ERR_PAYMENT_001", "Reservation not found"),
    RESERVATION_APPROVAL_ERROR("ERR_PAYMENT_002", "Error approving reservation"),
    // invalid parameters
    INVALID_PARAMETER("ERR_INVALID_001", "Invalid parameter"),

    // invalid JSON
    INVALID_JSON("ERR_JSON_001", "Invalid JSON"),

    // generic errors
    GENERIC_ERROR("ERR_GENERIC_001", "Generic error");

    private final String code;
    private final String message;
}
