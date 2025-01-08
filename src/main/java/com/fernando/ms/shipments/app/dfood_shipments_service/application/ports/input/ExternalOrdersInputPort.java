package com.fernando.ms.shipments.app.dfood_shipments_service.application.ports.input;

import java.util.List;

public interface ExternalOrdersInputPort {
    void verifyExistsOrderByIds(List<Long> ids);
}
