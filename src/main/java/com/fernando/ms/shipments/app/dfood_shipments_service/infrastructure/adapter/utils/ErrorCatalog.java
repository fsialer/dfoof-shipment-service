package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {
    SHIPMENT_NOT_FOUND("SHIPMENT_MS_001", "Shipment not found."),
    SHIPMENT_BAD_PARAMETERS("SHIPMENT_MS_002", "Invalid parameters for creation shipment"),
    STATUS_SHIPMENT_STRATEGY_ERROR("SHIPMENT_MS_003", "Status type selected is invalid."),
    INTERNAL_SERVER_ERROR("SHIPMENT_MS_000", "Internal server error.");


    private final String code;
    private final String message;
}
