package com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;

import java.util.List;

public interface ShipmentPersistencePort {
    List<Shipment> findAll();
}
