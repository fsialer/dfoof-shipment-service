package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.mapper.ShipmentPersistenceMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentEntity;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.repository.ShipmentJpaRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShipmentPersistenceAdapterTest {
    @Mock
    private ShipmentJpaRepository shipmentJpaRepository;
    @Mock
    private ShipmentPersistenceMapper shipmentPersistenceMapper;

    @InjectMocks
    private ShipmentPersistenceAdapter shipmentPersistenceAdapter;

    @Test
    @DisplayName("When Shipment Information Exists Expect A List Information Shipments")
    void When_ShipmentInformationExists_Expect_AListInformationShipments(){
        when(shipmentJpaRepository.findAll()).thenReturn(Collections.singletonList( TestUtilShipment.buildShipmentEntityMock()));
        when(shipmentPersistenceMapper.toShipments(anyList())).thenReturn(Collections.singletonList(TestUtilShipment.buildShipmentMock()));
        List<Shipment> shipments=shipmentPersistenceAdapter.findAll();
        assertEquals(1,shipments.size());
        Mockito.verify(shipmentJpaRepository, Mockito.times(1)).findAll();
        Mockito.verify(shipmentPersistenceMapper, Mockito.times(1)).toShipments(anyList());
    }

    @Test
    @DisplayName("When Shipment Information By Identifier Is Correct Expect Shipment Information Correct")
    void When_ShipmentInformationByIdentifierIsCorrect_Expect_ShipmentInformationCorrect(){
        when(shipmentJpaRepository.findById(anyLong())).thenReturn(Optional.of(TestUtilShipment.buildShipmentEntityMock()));
        when(shipmentPersistenceMapper.toShipment(any(ShipmentEntity.class))).thenReturn(TestUtilShipment.buildShipmentMock());
        Optional<Shipment> orderResponse=shipmentPersistenceAdapter.findById(1L);
        assertTrue(orderResponse.isPresent());
        Mockito.verify(shipmentJpaRepository,times(1)).findById(anyLong());
        Mockito.verify(shipmentPersistenceMapper,times(1)).toShipment(any(ShipmentEntity.class));
    }

    @Test
    @DisplayName("When Order Information Is Correct Expect Order Information To Be Saved")
    void When_OrderInformationIsCorrect_Expect_OrderInformationToBeSaved(){
        ShipmentEntity shipmentEntity= TestUtilShipment.buildShipmentEntityMock();
        Shipment shipment=TestUtilShipment.buildShipmentOrderDealerMock();
        when(shipmentJpaRepository.save(any(ShipmentEntity.class))).thenReturn(shipmentEntity);
        when(shipmentPersistenceMapper.toShipment(any(ShipmentEntity.class))).thenReturn(shipment);
        when(shipmentPersistenceMapper.toShipmentEntity(any(Shipment.class))).thenReturn(shipmentEntity);

        Shipment shipmentResponse=shipmentPersistenceAdapter.save(shipment);
        assertNotNull(shipmentResponse);
        assertEquals(shipment, shipmentResponse);
        Mockito.verify(shipmentJpaRepository,times(1)).save(any(ShipmentEntity.class));
        Mockito.verify(shipmentPersistenceMapper,times(1)).toShipment(any(ShipmentEntity.class));
        Mockito.verify(shipmentPersistenceMapper,times(1)).toShipmentEntity(any(Shipment.class));
    }

    @Test
    @DisplayName("When Change Status Of Shipment Expect Status Shipment Updated Correctly")
    void When_ChangeStatusOfShipment_Expect_StatusShipmentUpdatedCorrectly(){
        ShipmentEntity shipmentEntity= TestUtilShipment.buildShipmentDealerTrackingOrderEntityMock();
        Shipment shipment=TestUtilShipment.buildShipmentOrderDealerMock();
        when(shipmentJpaRepository.save(any(ShipmentEntity.class))).thenReturn(shipmentEntity);
        when(shipmentPersistenceMapper.toShipment(any(ShipmentEntity.class))).thenReturn(shipment);
        when(shipmentPersistenceMapper.toShipmentEntity(any(Shipment.class))).thenReturn(shipmentEntity);
        when(shipmentJpaRepository.findById(anyLong())).thenReturn(Optional.of(shipmentEntity));
        Shipment shipmentResponse=shipmentPersistenceAdapter.changeStatusShipment(shipment);
        assertNotNull(shipmentResponse);
        assertEquals(shipment, shipmentResponse);
        Mockito.verify(shipmentJpaRepository,times(1)).save(any(ShipmentEntity.class));
        Mockito.verify(shipmentPersistenceMapper,times(1)).toShipment(any(ShipmentEntity.class));
        Mockito.verify(shipmentPersistenceMapper,times(1)).toShipmentEntity(any(Shipment.class));
    }
}
