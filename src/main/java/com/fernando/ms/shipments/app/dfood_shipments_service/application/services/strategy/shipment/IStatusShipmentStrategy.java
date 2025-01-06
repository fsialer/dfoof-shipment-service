package com.fernando.ms.shipments.app.dfood_shipments_service.application.services.strategy.shipment;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;

public interface IStatusShipmentStrategy {
    String doOperation(Shipment shipment);
    boolean isApplicable(String statusTracking);
}
