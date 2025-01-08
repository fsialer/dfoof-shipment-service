package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient;

import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.client.DealerFeignClient;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.client.OrderFeignClient;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.mapper.DealerRestClientMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class OrderRestClientAdapterTest {
    @Mock
    private OrderFeignClient client;


    @InjectMocks
    private OrderRestClientAdapter orderRestClientAdapter;

    @Test
    @DisplayName("When Order Identifier Is Correct Expect Response204")
    void When_DealerIdentifierIsCorrect_Expect_Response204(){
        doNothing().when(client).verifyExistsByIds(anyList());
        assertDoesNotThrow(() -> orderRestClientAdapter.verifyExistsOrderByIds(List.of(1L)));
        Mockito.verify(client,times(1)).verifyExistsByIds(anyList());
    }
    @Test
    @DisplayName("When Order Identifier Status Is Correct Expect Response204")
    void When_DealerIdentifierStatusIsCorrect_Expect_Response204(){
        doNothing().when(client).verifyExistsStatusByIds(anyList(),anyString());
        assertDoesNotThrow(() -> orderRestClientAdapter.verifyExistsStatusOrderByIds(List.of(1L),"REGISTERED"));
        Mockito.verify(client,times(1)).verifyExistsStatusByIds(anyList(),anyString());
    }

}
