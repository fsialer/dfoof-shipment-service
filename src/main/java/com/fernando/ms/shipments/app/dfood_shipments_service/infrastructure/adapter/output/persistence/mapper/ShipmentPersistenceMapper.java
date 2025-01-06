package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.mapper;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Dealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.request.CreateShipmentRequest;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentDealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipmentPersistenceMapper {
    List<Shipment> toShipments(List<ShipmentEntity> shipments);
    Shipment toShipment(ShipmentEntity shipmentEntity);
//    @Mapping(target = "createdAt", expression = "java(mapCreatedAt(shipment))")
//    @Mapping(target = "updatedAt", expression = "java(mapUpdatedAt())")
    //@Mapping(target = "shipmentDealer",expression = "java(mapShipmentDealer(shipment))")
    ShipmentEntity toShipmentEntity(Shipment shipment);

//    default LocalDateTime mapUpdatedAt(){
//        return LocalDateTime.now();
//    }
//
//    default LocalDateTime mapCreatedAt(Shipment shipment){
//        return LocalDateTime.now();
//    }



}
