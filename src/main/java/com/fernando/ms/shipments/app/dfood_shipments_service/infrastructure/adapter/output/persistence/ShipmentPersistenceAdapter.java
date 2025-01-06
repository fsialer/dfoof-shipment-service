package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ShipmentPersistencePort;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Order;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.mapper.ShipmentPersistenceMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentEntity;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.repository.ShipmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        shipmentEntity.setShipmentDealerId(shipment.getDealer().getId());
        shipmentEntity.addTracking();
        return shipmentPersistenceMapper.toShipment(shipmentJpaRepository.save(shipmentEntity));
    }

    @Override
    public Shipment changeStatusShipment(Shipment shipment) {
        ShipmentEntity shipmentEntity=shipmentPersistenceMapper.toShipmentEntity(shipment);
        ShipmentEntity shipmentEntity2=shipmentJpaRepository.findById(shipment.getId()).get();
        shipmentEntity.setShipmentOrderList(
                new ArrayList<>(shipmentEntity2.getShipmentOrderList())
        );
        shipmentEntity.setTrackings(shipmentEntity2.getTrackings());
        shipmentEntity.addTracking();
        shipmentEntity.setShipmentDealer(shipmentEntity2.getShipmentDealer());
        shipmentEntity.setCreatedAt(shipmentEntity2.getCreatedAt());
        return shipmentPersistenceMapper.toShipment(shipmentJpaRepository.save(shipmentEntity));
    }


}
