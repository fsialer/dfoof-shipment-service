package com.fernando.ms.shipments.app.dfood_shipments_service.application.services.strategy.shipment;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.StatusShipmentRuleException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Tracking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DeliveredStatusShipmentStrategy implements IStatusShipmentStrategy{
    @Override
    public String doOperation(Shipment shipment) {
        List<String> status=shipment.getTrackings().stream().map(Tracking::getStatus).toList();
        if(status.contains("DELIVERED")){
            throw new StatusShipmentRuleException("Shipment already in progress..");
        }
        if(!status.contains("IN_PROGRESS")){
            throw new StatusShipmentRuleException("Shipment never was in progress.");
        }
        return "DELIVERED";
    }

    @Override
    public boolean isApplicable(String statusTracking) {
        return "DELIVERED".equalsIgnoreCase(statusTracking);
    }
}
