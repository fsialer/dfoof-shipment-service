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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
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
}
