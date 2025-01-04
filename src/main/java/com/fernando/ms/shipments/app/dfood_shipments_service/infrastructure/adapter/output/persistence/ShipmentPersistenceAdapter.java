package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ShipmentPersistencePort;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Order;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.mapper.ShipmentPersistenceMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentEntity;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.repository.ShipmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShipmentPersistenceAdapter implements ShipmentPersistencePort {
    private final ShipmentJpaRepository shipmentJpaRepository;
    private final ShipmentPersistenceMapper shipmentPersistenceMapper;
    @Override
    public List<Shipment> findAll() {
        return shipmentPersistenceMapper.toShipments(shipmentJpaRepository.findAll());
    }

    @Override
    public Optional<Shipment> findById(Long id) {
        return shipmentJpaRepository.findById(id).map(shipmentPersistenceMapper::toShipment);
    }

    @Override
    public Shipment save(Shipment shipment) {
        ShipmentEntity shipmentEntity=shipmentPersistenceMapper.toShipmentEntity(shipment);
        shipmentEntity.addShipmentOrder(shipment.getOrders().stream().map(Order::getId).toList());
        shipmentEntity.setShipmentDealer(shipment.getDealer().getId());
        shipmentEntity.addTracking();
        return shipmentPersistenceMapper.toShipment(shipmentJpaRepository.save(shipmentEntity));
    }


}
