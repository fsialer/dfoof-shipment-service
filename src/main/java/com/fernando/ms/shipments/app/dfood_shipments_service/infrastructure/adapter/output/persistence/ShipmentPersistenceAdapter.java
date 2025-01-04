package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ShipmentPersistencePort;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.mapper.ShipmentPersistenceMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.repository.ShipmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShipmentPersistenceAdapter implements ShipmentPersistencePort {
    private final ShipmentJpaRepository shipmentJpaRepository;
    private final ShipmentPersistenceMapper shipmentPersistenceMapper;
    @Override
    public List<Shipment> findAll() {
        return shipmentPersistenceMapper.toShipments(shipmentJpaRepository.findAll());
    }
}
