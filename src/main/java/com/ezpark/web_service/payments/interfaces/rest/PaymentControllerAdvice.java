package com.ezpark.web_service.payments.interfaces.rest;


import com.ezpark.web_service.payments.domain.model.exceptions.ExternalServiceCommunicationException;
import com.ezpark.web_service.payments.domain.model.exceptions.ReservationApprovalException;
import com.ezpark.web_service.payments.domain.model.exceptions.ReservationNotFoundException;
import com.ezpark.web_service.shared.interfaces.rest.resources.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static com.ezpark.web_service.payments.infrastructure.utils.PaymentErrorCatalog.*;

@RestControllerAdvice(basePackages = "com.ezpark.web_service.payments")
public class PaymentControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public ErrorResource handleReservationNotFoundException() {
        ErrorResource response = new ErrorResource();
        response.setCode(RESERVATION_NOT_FOUND.getCode());
        response.setMessage(RESERVATION_NOT_FOUND.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationApprovalException.class)
    public ErrorResource handleReservationApprovalException() {
        ErrorResource response = new ErrorResource();
        response.setCode(RESERVATION_APPROVAL_ERROR.getCode());
        response.setMessage(RESERVATION_APPROVAL_ERROR.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return response;
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ExternalServiceCommunicationException.class)
    public ErrorResource handleExternalServiceCommunicationException(ExternalServiceCommunicationException exception) {
        ErrorResource response = new ErrorResource();
        response.setCode(EXTERNAL_SERVICE_UNAVAILABLE.getCode());
        response.setMessage(EXTERNAL_SERVICE_UNAVAILABLE.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return response;
    }


    // invalid JSON
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResource handleHttpMessageNotReadableException() {
        ErrorResource response = new ErrorResource();
        response.setCode(INVALID_JSON.getCode());
        response.setMessage(INVALID_JSON.getMessage());
        response.setTimeStamp(LocalDateTime.now());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResource handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        ErrorResource response = new ErrorResource();
        response.setCode(INVALID_PARAMETER.getCode());
        response.setMessage(INVALID_PARAMETER.getMessage());
        response.setDetails(
                result.getFieldErrors().stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .sorted()
                        .toList());
        response.setTimeStamp(LocalDateTime.now());
        return response;
    }



    // generic errors
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResource handleGenericException(Exception exception) {
        ErrorResource response = new ErrorResource();
        response.setCode(GENERIC_ERROR.getCode());
        response.setMessage(GENERIC_ERROR.getMessage());
        response.setDetails(List.of(exception.getMessage()));
        response.setTimeStamp(LocalDateTime.now());
        return response;
    }
}
