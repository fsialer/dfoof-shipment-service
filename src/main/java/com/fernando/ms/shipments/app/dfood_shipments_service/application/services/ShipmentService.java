package com.fernando.ms.shipments.app.dfood_shipments_service.application.services;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ExternalDealersInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ExternalOrdersInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ShipmentInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ExternalDealersOutputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ExternalOrdersOutputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ShipmentPersistencePort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.services.strategy.shipment.IStatusShipmentStrategy;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.ShipmentNotFoundException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.StatusShipmentStrategyException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService implements ShipmentInputPort, ExternalDealersInputPort, ExternalOrdersInputPort {

    private final ShipmentPersistencePort shipmentPersistencePort;
    private final List<IStatusShipmentStrategy>  orderStrategyList;
    private final ExternalDealersOutputPort externalDealersOutputPort;
    private final ExternalOrdersOutputPort externalOrdersOutputPort;

    @Override
    public List<Shipment> findAll() {
        return shipmentPersistencePort.findAll();
    }

    @Override
    public Shipment findById(Long id) {
        return shipmentPersistencePort.findById(id).orElseThrow(ShipmentNotFoundException::new);
    }

    @Override
    public Shipment save(Shipment shipment) {

        IStatusShipmentStrategy shipmentStrategy=orderStrategyList.stream()
                .filter(strategy->strategy.isApplicable("PENDING"))
                .findFirst()
                .orElseThrow(()->new StatusShipmentStrategyException("Status shipment not found: PENDING"));
        shipment.setStatusShipment(shipmentStrategy.doOperation(shipment));
        return shipmentPersistencePort.save(shipment);
    }

    @Override
    public Shipment changeStatusShipment(Long id, String status) {
        IStatusShipmentStrategy shipmentStrategy=orderStrategyList.stream()
                .filter(strategy->strategy.isApplicable(status))
                .findFirst()
                .orElseThrow(()->new StatusShipmentStrategyException("Status shipment not found: "+status));
        return shipmentPersistencePort.findById(id)
                .map(shipment -> {
                    shipment.setStatusShipment(shipmentStrategy.doOperation(shipment));
                    return shipmentPersistencePort.changeStatusShipment(shipment);
                })
                .orElseThrow(ShipmentNotFoundException::new);
    }

    @Override
    public void verifyExistsDealersById(Long id) {
        externalDealersOutputPort.verifyExistsDealersById(id);
    }

    @Override
    public void verifyExistsOrderByIds(List<Long> ids) {
        externalOrdersOutputPort.verifyExistsOrderByIds(ids);
    }
}
