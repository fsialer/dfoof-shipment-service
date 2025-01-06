package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ShipmentInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.mapper.ShipmentRestMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.request.CreateShipmentRequest;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ShipmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(shipmentRestMapper.toShipmentResponse(shipmentInputPort.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ShipmentResponse> save(@Valid @RequestBody CreateShipmentRequest rq){
        ShipmentResponse response=shipmentRestMapper.toShipmentResponse(shipmentInputPort.save(shipmentRestMapper.toShipment(rq)));
        return ResponseEntity.created(URI.create("/shipments/".concat(response.getId().toString()))).body(response);
    }

    @PutMapping("{id}/change-status/{status}")
    public ResponseEntity<ShipmentResponse> changeStatus(@PathVariable(name = "id") Long id,@PathVariable(name = "status") String status){
        ShipmentResponse orderResponse=shipmentRestMapper.toShipmentResponse(shipmentInputPort.changeStatusShipment(id,status));
        return ResponseEntity.ok().body(orderResponse);
    }
}
