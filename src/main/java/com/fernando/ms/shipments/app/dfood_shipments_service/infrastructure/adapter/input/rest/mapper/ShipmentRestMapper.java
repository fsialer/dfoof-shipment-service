package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.mapper;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ShipmentResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipmentRestMapper {
    List<ShipmentResponse> toShipmentsResponse(List<Shipment> dealers);

}
