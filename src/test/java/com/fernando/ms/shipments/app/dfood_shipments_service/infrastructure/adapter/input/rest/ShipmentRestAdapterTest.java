package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ExternalDealersInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ShipmentInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.mapper.ShipmentRestMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.request.CreateShipmentRequest;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @MockBean
    private ExternalDealersInputPort externalDealersInputPort;


    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        objectMapper=new ObjectMapper();
    }

    @Test
    @DisplayName("When Shipmentts Are Availability Expect Shipments Information Successfully")
    void When_ShipmentsAreAvailability_Expect_ShipmentsInformationSuccessfully() throws Exception {

        Shipment order = TestUtilShipment.buildShipmentMock();
        List<ShipmentResponse> shipmentsResponse= Collections.singletonList(TestUtilShipment.buildShipmentResponseMock());

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

    @Test
    @DisplayName("When Shipment Identifier Is Valid Expect Shipment Information Successfully")
    void When_ShipmentIdentifierIsValid_Expect_ShipmentInformationSuccessfully() throws Exception {

        when(shipmentInputPort.findById(anyLong()))
                .thenReturn(TestUtilShipment.buildShipmentMock());

        when(shipmentRestMapper.toShipmentResponse(any(Shipment.class)))
                .thenReturn(TestUtilShipment.buildShipmentResponseMock());

        mockMvc.perform(get("/shipments/{id}",1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

        Mockito.verify(shipmentInputPort,times(1)).findById(anyLong());
        Mockito.verify(shipmentRestMapper,times(1)).toShipmentResponse(any(Shipment.class));
    }

    @Test
    @DisplayName("When Shipment Information Is Correct Expect Shipment Information Saved Successfully")
    void When_ShipmentInformationIsCorrect_Expect_ShipmentInformationSavedSuccessfully() throws Exception {

        when(shipmentInputPort.save(any(Shipment.class)))
                .thenReturn(TestUtilShipment.buildShipmentMock());

        when(shipmentRestMapper.toShipment(any(CreateShipmentRequest.class)))
                .thenReturn(TestUtilShipment.buildShipmentOrderDealerMock());
        when(shipmentRestMapper.toShipmentResponse(any(Shipment.class)))
                .thenReturn(TestUtilShipment.buildShipmentResponseMock());

        doNothing().when(externalDealersInputPort).verifyExistsDealersById(anyLong());

        mockMvc.perform(post("/shipments").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestUtilShipment.buildCreateShipmentRequestMok())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

        Mockito.verify(shipmentInputPort,times(1)).save(any(Shipment.class));
        Mockito.verify(shipmentRestMapper,times(1)).toShipmentResponse(any(Shipment.class));
        Mockito.verify(shipmentRestMapper,times(1)).toShipment(any(CreateShipmentRequest.class));
        Mockito.verify(externalDealersInputPort,times(1)).verifyExistsDealersById(anyLong());
    }

    @Test
    @DisplayName("When Change Status Shipment Is Correct Expect Status Shipment Updated Successfully")
    void When_ChangeStatusShipmentIsCorrect_Expect_StatusShipmentUpdatedSuccessfully() throws Exception {

        when(shipmentInputPort.changeStatusShipment(anyLong(),anyString()))
                .thenReturn(TestUtilShipment.buildShipmentMock());

//        when(shipmentRestMapper.toShipment(any(CreateShipmentRequest.class)))
//                .thenReturn(TestUtilShipment.buildShipmentOrderDealerMock());
        when(shipmentRestMapper.toShipmentResponse(any(Shipment.class)))
                .thenReturn(TestUtilShipment.buildShipmentResponseMock());

        mockMvc.perform(put("/shipments/{id}/change-status/{status}",1L,"IN_PROGRESS").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestUtilShipment.buildCreateShipmentRequestMok())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

        Mockito.verify(shipmentInputPort,times(1)).changeStatusShipment(anyLong(),anyString());
        Mockito.verify(shipmentRestMapper,times(1)).toShipmentResponse(any(Shipment.class));
        //Mockito.verify(shipmentRestMapper,times(1)).toShipment(any(CreateShipmentRequest.class));
    }
}
