package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ShipmentInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.mapper.ShipmentRestMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ShipmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shipments")
public class ShipmentRestAdapter {
    private final ShipmentInputPort shipmentInputPort;
    private final ShipmentRestMapper shipmentRestMapper;

    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> findAll(){
        return ResponseEntity.ok().body(shipmentRestMapper.toShipmentsResponse(shipmentInputPort.findAll()));
    }
}
