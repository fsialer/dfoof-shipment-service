package com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.services;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ExternalDealersOutputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ExternalOrdersOutputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ShipmentPersistencePort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.services.ShipmentService;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.services.strategy.shipment.IStatusShipmentStrategy;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.ShipmentNotFoundException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.StatusShipmentStrategyException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.utils.TestUtilShipment;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceTest {
    @Mock
    private ShipmentPersistencePort shipmentPersistencePort;

    @Mock
    private  IStatusShipmentStrategy statusShipmentStrategy;

    private List<IStatusShipmentStrategy> statusShipmentStrategyList;

    @Mock
    private ExternalDealersOutputPort externalDealersOutputPort;

    @Mock
    private ExternalOrdersOutputPort externalOrdersOutputPort;

    @InjectMocks
    private ShipmentService shipmentService;

    @BeforeEach
    void setUp() {
        statusShipmentStrategyList = List.of(statusShipmentStrategy);
        shipmentService = new ShipmentService(shipmentPersistencePort, statusShipmentStrategyList,externalDealersOutputPort,externalOrdersOutputPort);
    }

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

    @Test
    @DisplayName("When Shipment Information By Identifier Is Correct Expect Shipment Information Correct")
    void When_ShipmentInformationByIdentifierIsCorrect_Expect_ShipmentInformationCorrect(){

        when(shipmentPersistencePort.findById(anyLong())).thenReturn(Optional.of(TestUtilShipment.buildShipmentMock()));
        Shipment shipmentResponse=shipmentService.findById(1L);
        assertNotNull(shipmentResponse);
        Mockito.verify(shipmentPersistencePort,times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Expect ShipmentNotFoundException When Shipment Information By Identifier Is Incorrect")
    void Expect_ShipmentNotFoundException_When_ShipmentInformationByIdentifierIsIncorrect(){
        when(shipmentPersistencePort.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ShipmentNotFoundException.class,()->shipmentService.findById(1L));
        Mockito.verify(shipmentPersistencePort,times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("When Shipment Information Is Correct Expect Shipment Information Saved Correctly")
    void When_ShipmentInformationIsCorrect_Expect_ShipmentInformationSavedCorrectly(){
        Shipment shipment= TestUtilShipment.buildShipmentOrderDealerMock();
        when(statusShipmentStrategy.isApplicable("PENDING")).thenReturn(true);
        when(statusShipmentStrategy.doOperation(any(Shipment.class))).thenReturn("PENDING");
        when(shipmentPersistencePort.save(shipment)).thenReturn(shipment);
        Shipment shipmentResponse=shipmentService.save(shipment);
        assertNotNull(shipmentResponse);
        Mockito.verify(statusShipmentStrategy,times(1)).isApplicable(anyString());
        Mockito.verify(statusShipmentStrategy,times(1)).doOperation(any(Shipment.class));
        Mockito.verify(shipmentPersistencePort,times(1)).save(shipment);
    }

    @Test
    @DisplayName("Expect StatusShipmentStrategyException When Shipment Information Is Not Correctly")
    void Expect_StatusShipmentStrategyException_When_ShipmentInformationIsNotCorrectly(){
        Shipment shipment= TestUtilShipment.buildShipmentOrderDealerMock();
        when(statusShipmentStrategy.isApplicable("PENDING")).thenReturn(false);
        //when(statusShipmentStrategy.doOperation(any(Shipment.class))).thenReturn("PENDING");
        //when(shipmentPersistencePort.save(shipment)).thenReturn(shipment);
        StatusShipmentStrategyException exception =assertThrows(StatusShipmentStrategyException.class,()->{
            shipmentService.save(shipment);
        });
        //Shipment shipmentResponse=;
        //assertNotNull(shipmentResponse);
        assertEquals("Status shipment not found: PENDING", exception.getMessage());
        Mockito.verify(statusShipmentStrategy,times(1)).isApplicable(anyString());
        Mockito.verify(statusShipmentStrategy,times(0)).doOperation(any(Shipment.class));
        Mockito.verify(shipmentPersistencePort,times(0)).save(shipment);
    }

    @Test
    @DisplayName("When change Status Of Shipment Correctly Expect Change Status Of Shipment Information Updated Correctly")
    void When_ChangeStatusOfShipmentCorrectly_Expect_ChangeStatusOfShipmentInformationUpdatedCorrectly(){
        Shipment shipment= TestUtilShipment.buildShipmentOrderDealerMock();
        when(statusShipmentStrategy.isApplicable("IN_PROGRESS")).thenReturn(true);
        when(statusShipmentStrategy.doOperation(any(Shipment.class))).thenReturn("IN_PROGRESS");
        when(shipmentPersistencePort.findById(anyLong())).thenReturn(Optional.of(shipment));
        when(shipmentPersistencePort.changeStatusShipment(shipment)).thenReturn(shipment);
        Shipment shipmentResponse=shipmentService.changeStatusShipment(1L,"IN_PROGRESS");
        assertNotNull(shipmentResponse);
        Mockito.verify(statusShipmentStrategy,times(1)).isApplicable(anyString());
        Mockito.verify(statusShipmentStrategy,times(1)).doOperation(any(Shipment.class));
        Mockito.verify(shipmentPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(shipmentPersistencePort,times(1)).changeStatusShipment(shipment);
    }

    @Test
    @DisplayName("When Dealer Identifier Is Correct Expect Result Void")
    void When_DealerIdentifierIsCorrect_ExpectResultVoid(){
        doNothing().when(externalDealersOutputPort).verifyExistsDealersById(anyLong());
        shipmentService.verifyExistsDealersById(1L);
        Mockito.verify(externalDealersOutputPort,times(1)).verifyExistsDealersById(anyLong());
    }
}
