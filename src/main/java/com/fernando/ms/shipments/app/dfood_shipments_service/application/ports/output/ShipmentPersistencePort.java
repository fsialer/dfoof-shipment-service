package com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;

import java.util.List;
import java.util.Optional;

public interface ShipmentPersistencePort {
    List<Shipment> findAll();
    Optional<Shipment> findById(Long id);
}
