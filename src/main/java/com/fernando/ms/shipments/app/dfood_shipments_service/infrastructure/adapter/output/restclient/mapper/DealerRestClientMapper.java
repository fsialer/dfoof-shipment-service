package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.mapper;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Dealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.models.response.DealerClientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealerRestClientMapper {

    Dealer toDealer(DealerClientResponse dealer);
}
