package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.ShipmentNotFoundException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.StatusShipmentRuleException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.StatusShipmentStrategyException;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Collections;

import static com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.enums.ErrorType.SYSTEM;
import static com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.utils.ErrorCatalog.*;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ShipmentNotFoundException.class)
    public ErrorResponse handleOrderNotFoundException() {

        return ErrorResponse.builder()
                .code(SHIPMENT_NOT_FOUND.getCode())
                .type(FUNCTIONAL)
                .message(SHIPMENT_NOT_FOUND.getMessage())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        return ErrorResponse.builder()
                .code(SHIPMENT_BAD_PARAMETERS.getCode())
                .type(FUNCTIONAL)
                .message(SHIPMENT_BAD_PARAMETERS.getMessage())
                .details(bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage())
                        .toList())
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StatusShipmentStrategyException.class)
    public ErrorResponse handleStatusShipmentStrategyException(StatusShipmentStrategyException e) {

        return ErrorResponse.builder()
                .code(STATUS_SHIPMENT_STRATEGY_ERROR.getCode())
                .type(FUNCTIONAL)
                .message(STATUS_SHIPMENT_STRATEGY_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StatusShipmentRuleException.class)
    public ErrorResponse handleStatusShipmentRuleException(StatusShipmentRuleException e) {

        return ErrorResponse.builder()
                .code(STATUS_SHIPMENT_RULES_ERROR.getCode())
                .type(FUNCTIONAL)
                .message(STATUS_SHIPMENT_RULES_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        return ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.getCode())
                .type(SYSTEM)
                .message(INTERNAL_SERVER_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build();
    }
}
