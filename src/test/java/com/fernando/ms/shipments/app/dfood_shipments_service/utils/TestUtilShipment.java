package com.fernando.ms.shipments.app.dfood_shipments_service.utils;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.enums.StatusShipmentEnum;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Dealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Order;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Tracking;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.request.CreateShipmentRequest;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ShipmentResponse;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentDealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentEntity;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentOrder;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.TrackingEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static ShipmentEntity buildShipmenEntityMock(){
        return ShipmentEntity.builder()
                .id(1L)
                .dateShipment(LocalDateTime.now())
                .address("Lambayeque chiclayo pimentel, calle 3 de otcubre 575")
                .reference("frente a la plaza de armas")
                .geoLocation("-6.7686,-79.8417")
                .statusShipment("PENDING")
                .build();
    }

    public static ShipmentEntity buildShipmentDealerTrackingOrderEntityMock(){
        return ShipmentEntity.builder()
                .id(1L)
                .dateShipment(LocalDateTime.now())
                .address("Lambayeque chiclayo pimentel, calle 3 de otcubre 575")
                .reference("frente a la plaza de armas")
                .geoLocation("-6.7686,-79.8417")
                .statusShipment("IN_PROGRESS")
                .shipmentOrderList(new ArrayList<>(List.of(ShipmentOrder.builder().id(1L).orderId(1L).build())))
                .shipmentDealer(ShipmentDealer.builder().id(1L).dealerId(1L).build())
                .trackings(new ArrayList<>(List.of(
                        TrackingEntity.builder().id(1L).status("PENDING").build(),
                        TrackingEntity.builder().id(1L).status("IN_PROGRESS").build()
                )))
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

    public static Shipment buildShipmentOrderDealerMock(){
        return Shipment.builder()
                .id(1L)
                .dateShipment(LocalDateTime.now())
                .address("Lambayeque chiclayo pimentel, calle 3 de otcubre 575")
                .reference("frente a la plaza de armas")
                .geoLocation("-6.7686,-79.8417")
                .statusShipment("PENDING")
                .dealer(Dealer.builder().id(1L).build())
                .orders(new ArrayList<>(List.of(Order.builder().id(1L).build())))
                .trackings(new ArrayList<>(List.of(Tracking.builder().id(1L).status("PENDING").build())))
                .build();
    }

    public static CreateShipmentRequest buildCreateShipmentRequestMok(){
        return CreateShipmentRequest.builder()
                .address("Lambayeque chiclayo pimentel, calle 3 de otcubre 575")
                .reference("")
                .geoLocation("-6.7686,-79.8417")
                .orders(new ArrayList<>(List.of(1L,2L)))
                .dealerId(1L)
                .build();
    }
}
