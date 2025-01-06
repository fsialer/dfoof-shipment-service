package com.fernando.ms.shipments.app.dfood_shipments_service.application.services.strategy.shipment;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PendingStatusShipmentStrategy implements IStatusShipmentStrategy {
    @Override
    public String doOperation(Shipment shipment) {
        return "PENDING";
    }

    @Override
    public boolean isApplicable(String statusTracking) {
        return "PENDING".equalsIgnoreCase(statusTracking);
    }
}
