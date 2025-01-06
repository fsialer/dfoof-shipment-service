package com.fernando.ms.shipments.app.dfood_shipments_service.application.services.strategy.shipment;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.StatusShipmentRuleException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Tracking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class InProgressStatusShipmentStrategy implements IStatusShipmentStrategy{
    @Override
    public String doOperation(Shipment shipment) {
        List<String> status=shipment.getTrackings().stream().map(Tracking::getStatus).toList();
        System.out.println("size: "+status.size());
        if(status.contains("IN_PROGRESS")){
            throw new StatusShipmentRuleException("Order already in progress.");
        }
        if(!status.contains("PENDING")){
            throw new StatusShipmentRuleException("Order never was pending.");
        }
        return "IN_PROGRESS";
    }

    @Override
    public boolean isApplicable(String statusTracking) {
        return "IN_PROGRESS".equalsIgnoreCase(statusTracking);
    }
}
