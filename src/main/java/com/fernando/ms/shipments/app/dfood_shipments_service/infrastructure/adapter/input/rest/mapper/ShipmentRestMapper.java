package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.mapper;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Dealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Order;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.request.CreateShipmentRequest;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ShipmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipmentRestMapper {
    List<ShipmentResponse> toShipmentsResponse(List<Shipment> dealers);
    ShipmentResponse toShipmentResponse(Shipment dealer);

    @Mapping(target = "orders", expression = "java(mapOrders(rq))")
    @Mapping(target = "dealer", expression = "java(mapDealer(rq))")
    Shipment toShipment(CreateShipmentRequest rq);


    default  List<Order> mapOrders(CreateShipmentRequest rq){
        List<Order> orders = new ArrayList<>();
        rq.getOrders().forEach(orderId->{
            orders.add(Order.builder().id(orderId).build());
        });
        return orders;
    }

    default Dealer mapDealer(CreateShipmentRequest rq){
        return Dealer.builder().id(rq.getDealerId()).build();
    }

//    default String mapStatusShipment(){
//        return "PENDING";
//    }

}
