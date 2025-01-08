package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient;

import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.client.DealerFeignClient;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DealerRestClientAdapterTest {
    @Mock
    private  DealerFeignClient client;
    @Mock
    private  DealerRestClientMapper dealerRestClientMapper;

    @InjectMocks
    private DealerRestClientAdapter dealerRestClientAdapter;

    @Test
    @DisplayName("When Dealer Identifier Is Correct Expect Response204")
    void When_DealerIdentifierIsCorrect_Expect_Response204(){
        doNothing().when(client).verifyExistsByIds(anyLong());
        assertDoesNotThrow(() -> dealerRestClientAdapter.verifyExistsDealersById(1L));
        Mockito.verify(client,times(1)).verifyExistsByIds(anyLong());
    }


}
