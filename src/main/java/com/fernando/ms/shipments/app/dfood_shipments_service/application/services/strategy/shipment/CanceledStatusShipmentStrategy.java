package com.fernando.ms.shipments.app.dfood_shipments_service.application.services.strategy.shipment;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.StatusShipmentRuleException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Tracking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CanceledStatusShipmentStrategy implements IStatusShipmentStrategy{
    @Override
    public String doOperation(Shipment shipment) {
        List<String> status=shipment.getTrackings().stream().map(Tracking::getStatus).toList();
        if(status.contains("IN_PROGRESS") ||status.contains("DELIVERED")){
            throw new StatusShipmentRuleException("Shipment can't be canceled.");
        }
        return "CANCELED";
    }

    @Override
    public boolean isApplicable(String statusTracking) {
        return "CANCELED".equalsIgnoreCase(statusTracking);
    }
}
