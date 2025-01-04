package com.fernando.ms.shipments.app.dfood_shipments_service.utils;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.enums.StatusShipmentEnum;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ShipmentResponse;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentEntity;

import java.time.LocalDateTime;

public class TestUtilShipment {
    public static Shipment buildShipmentMock(){
        return Shipment.builder()
                .id(1L)
                .dateShipment(LocalDateTime.now())
                .address("Lambayeque chiclayo pimentel, calle 3 de otcubre 575")
                .reference("frente a la plaza de armas")
                .geoLocation("-6.7686,-79.8417")
                .statusShipment("PENDING")
                .build();
    }

    public static ShipmentEntity buildShipmentEntityMock(){
        return ShipmentEntity.builder()
                .id(1L)
                .dateShipment(LocalDateTime.now())
                .address("Lambayeque chiclayo pimentel, calle 3 de otcubre 575")
                .reference("frente a la plaza de armas")
                .geoLocation("-6.7686,-79.8417")
                .statusShipment("PENDING")
                .build();
    }

    public static ShipmentResponse buildShipmentResponseMock(){
        return ShipmentResponse.builder()
                .id(1L)
                .dateShipment(LocalDateTime.now())
                .address("Lambayeque chiclayo pimentel, calle 3 de otcubre 575")
                .reference("frente a la plaza de armas")
                .geoLocation("-6.7686,-79.8417")
                .statusShipment("PENDING")
                .build();
    }
}
