package com.ezpark.web_service.payments.domain.model.exceptions;

/**
 * @author amner
 * @date 7/06/2025
 */
public class ExternalServiceCommunicationException extends RuntimeException {
    public ExternalServiceCommunicationException(String message) {
        super(message);
    }
    public ExternalServiceCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
