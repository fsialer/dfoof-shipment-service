package com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.services;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ShipmentPersistencePort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.services.ShipmentService;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.utils.TestUtilShipment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceTest {
    @Mock
    private ShipmentPersistencePort shipmentPersistencePort;

    @InjectMocks
    private ShipmentService shipmentService;

    @Test
    @DisplayName("When Shipment Information Exists  Expect A List Information Shipments")
    void When_ShipmentInformationExists_Expect_AListInformationShipments(){
        Shipment shipment= TestUtilShipment.buildShipmentMock();
        when(shipmentPersistencePort.findAll()).thenReturn(Collections.singletonList(shipment));
        List<Shipment> shipments=shipmentService.findAll();
        assertEquals(1,shipments.size());
        Mockito.verify(shipmentPersistencePort,times(1)).findAll();
    }

    @Test
    @DisplayName("When Shipment Information Not Exists  Expect A List Void")
    void When_ShipmentInformationExists_Expect_AListVoid(){
        when(shipmentPersistencePort.findAll()).thenReturn(Collections.emptyList());
        List<Shipment> shipments=shipmentService.findAll();
        assertEquals(0,shipments.size());
        Mockito.verify(shipmentPersistencePort,times(1)).findAll();
    }
}
