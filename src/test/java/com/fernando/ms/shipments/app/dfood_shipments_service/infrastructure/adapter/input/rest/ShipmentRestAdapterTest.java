package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ShipmentInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.mapper.ShipmentRestMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ShipmentResponse;
import com.fernando.ms.shipments.app.dfood_shipments_service.utils.TestUtilShipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShipmentRestAdapter.class)
public class ShipmentRestAdapterTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ShipmentInputPort shipmentInputPort;

    @MockBean
    private ShipmentRestMapper shipmentRestMapper;


    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        objectMapper=new ObjectMapper();
    }

    @Test
    @DisplayName("When Shipmentts Are Availability Expect Shipments Information Successfully")
    void When_ShipmentsAreAvailability_Expect_ShipmentsInformationSuccessfully() throws Exception {

        Shipment order = TestUtilShipment.buildShipmentMock();
        List<ShipmentResponse> shipmentsResponse= Collections.singletonList(TestUtilShipment.buildShipmentsResponseMock());

        when(shipmentInputPort.findAll())
                .thenReturn(Collections.singletonList(order));

        when(shipmentRestMapper.toShipmentsResponse(anyList()))
                .thenReturn(shipmentsResponse);

        mockMvc.perform(get("/shipments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(1))
                .andDo(print());

        Mockito.verify(shipmentInputPort,times(1)).findAll();
        Mockito.verify(shipmentRestMapper,times(1)).toShipmentsResponse(anyList());
    }
}
