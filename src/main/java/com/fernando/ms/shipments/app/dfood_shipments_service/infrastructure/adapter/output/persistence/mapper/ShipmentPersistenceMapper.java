package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.mapper;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipmentPersistenceMapper {
    List<Shipment> toShipments(List<ShipmentEntity> shipments);
    Shipment toShipment(ShipmentEntity shipment);
}
