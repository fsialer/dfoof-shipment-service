package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient;

import com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output.ExternalOrdersOutputPort;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.client.DealerFeignClient;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.client.OrderFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderRestClientAdapter implements ExternalOrdersOutputPort {
    private final OrderFeignClient client;

    @Override
    public void verifyExistsOrderByIds(List<Long> ids) {
        client.verifyExistsByIds(ids);
    }
}
