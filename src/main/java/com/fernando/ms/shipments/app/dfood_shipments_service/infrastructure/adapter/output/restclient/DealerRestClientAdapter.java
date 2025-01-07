package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ExternalDealersOutputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.client.DealerFeignClient;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.mapper.DealerRestClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DealerRestClientAdapter implements ExternalDealersOutputPort {
    private final DealerFeignClient client;
    private final DealerRestClientMapper dealerRestClientMapper;

    @Override
    public void verifyExistsDealersById(Long id) {
        client.verifyExistsByIds(id);
    }
}
