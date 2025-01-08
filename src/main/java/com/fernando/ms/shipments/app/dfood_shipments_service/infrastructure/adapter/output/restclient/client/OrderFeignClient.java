package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="orders-service",url="${orders-service.url}")
public interface OrderFeignClient {
    @GetMapping("verify-exists-by-ids")
    void verifyExistsByIds(@RequestParam List<Long> ids);

    @GetMapping("verify-exists-status-by-ids")
    void verifyExistsStatusByIds(@RequestParam List<Long> ids,@RequestParam String status);


}
