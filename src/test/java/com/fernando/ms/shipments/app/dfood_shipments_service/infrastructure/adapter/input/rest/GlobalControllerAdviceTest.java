package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input.ShipmentInputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.ShipmentNotFoundException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.exceptions.StatusShipmentStrategyException;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Shipment;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.mapper.ShipmentRestMapper;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.request.CreateShipmentRequest;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response.ErrorResponse;
import com.fernando.ms.shipments.app.dfood_shipments_service.utils.TestUtilShipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.enums.ErrorType.SYSTEM;
import static com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.utils.ErrorCatalog.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ShipmentRestAdapter.class})
public class GlobalControllerAdviceTest {

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
    @DisplayName("Expect OrderNotFoundException When Order Identifier Is Unknown")
    void Expect_OrderNotFoundException_When_OrderIdentifierIsUnknown() throws Exception {
        when(shipmentInputPort.findById(anyLong()))
                .thenThrow(new ShipmentNotFoundException());
        mockMvc.perform(get("/shipments/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result->{
                    ErrorResponse errorResponse=objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertAll(
                            ()->assertEquals(SHIPMENT_NOT_FOUND.getCode(),errorResponse.getCode()),
                            ()->assertEquals(FUNCTIONAL,errorResponse.getType()),
                            ()->assertEquals(SHIPMENT_NOT_FOUND.getMessage(),errorResponse.getMessage()),
                            ()->assertNotNull(errorResponse.getTimestamp())
                    );
                });

    }

    @Test
    @DisplayName("Expect MethodArgumentNotValidException When Shipment Information Is Invalid")
    void Expect_MethodArgumentNotValidException_When_ShipmentInformationIsInvalid() throws Exception {
        mockMvc.perform(post("/shipments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(result->{
                    ErrorResponse errorResponse=objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class
                    );
                    assertAll(
                            ()->assertEquals(SHIPMENT_BAD_PARAMETERS.getCode(),errorResponse.getCode()),
                            ()->assertEquals(FUNCTIONAL,errorResponse.getType()),
                            ()->assertEquals(SHIPMENT_BAD_PARAMETERS.getMessage(),errorResponse.getMessage()),
                            ()->assertNotNull(errorResponse.getDetails()),
                            ()->assertNotNull(errorResponse.getTimestamp())
                    );
                });
    }

    @Test
    @DisplayName("Expect StatusShipmentStrategyException When Status Of Shipment Is Invalid")
    void Expect_StatusShipmentStrategyException_WhenStatusOfShipmentIsInvalid() throws Exception {
        CreateShipmentRequest createOrderRequest= TestUtilShipment.buildCreateShipmentRequestMok();
        Shipment shipment = TestUtilShipment.buildShipmentMock();

        when(shipmentRestMapper.toShipment(any(CreateShipmentRequest.class)))
                .thenReturn(shipment);
        when(shipmentInputPort.save(any(Shipment.class)))
                .thenThrow(new StatusShipmentStrategyException("Status shipment not found: PENDING"));
        mockMvc.perform(post("/shipments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createOrderRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    ErrorResponse errorResponse = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            ErrorResponse.class
                    );
                    assertAll(
                            () -> assertEquals(STATUS_SHIPMENT_STRATEGY_ERROR.getCode(), errorResponse.getCode()),
                            () -> assertEquals(FUNCTIONAL, errorResponse.getType()),
                            () -> assertEquals(STATUS_SHIPMENT_STRATEGY_ERROR.getMessage(), errorResponse.getMessage())
                    );
                });
    }


    @Test
    void Expect_RuntimeException_When_OrderInformationIsInvalid() throws Exception {
        when(shipmentInputPort.findAll())
                .thenThrow(new RuntimeException("Generic error"));
        mockMvc.perform(post("/shipments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result->{
                    ErrorResponse errorResponse=objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class
                    );

                    assertAll(
                            ()->assertEquals(INTERNAL_SERVER_ERROR.getCode(),errorResponse.getCode()),
                            ()->assertEquals(SYSTEM,errorResponse.getType()),
                            ()->assertEquals(INTERNAL_SERVER_ERROR.getMessage(),errorResponse.getMessage()),
                            ()->assertNotNull(errorResponse.getDetails()),
                            ()->assertNotNull(errorResponse.getTimestamp())
                    );
                });
    }
}
