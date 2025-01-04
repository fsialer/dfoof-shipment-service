package com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;

import java.util.List;

public interface ShipmentInputPort {
    List<Shipment> findAll();
    Shipment findById(Long id);
    Shipment save(Shipment shipment);
}
   