package com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.output;

import java.util.List;

public interface ExternalOrdersOutputPort {
    void verifyExistsOrderByIds(List<Long> ids);
    void verifyExistsStatusOrderByIds(List<Long> ids,String status);
}
