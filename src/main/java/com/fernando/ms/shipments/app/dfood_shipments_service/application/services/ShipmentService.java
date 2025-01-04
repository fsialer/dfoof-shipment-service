package com.fernando.ms.shipments.app.dfood_shipments_service.application.services;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ShipmentInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ShipmentPersistencePort;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.ShipmentNotFoundException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService implements ShipmentInputPort {

    private final ShipmentPersistencePort shipmentPersistencePort;

    @Override
    public List<Shipment> findAll() {
        return shipmentPersistencePort.findAll();
    }

    @Override
    public Shipment findById(Long id) {
        return shipmentPersistencePort.findById(id).orElseThrow(ShipmentNotFoundException::new);
    }
}
