package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="dealers-service",url="${dealers-service.url}")
public interface DealerFeignClient {
    @GetMapping("verify-exists-by-id")
    void verifyExistsByIds(@RequestParam Long id);
}
