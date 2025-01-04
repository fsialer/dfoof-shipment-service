package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.response;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Dealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Order;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Tracking;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentResponse {
    private Long id;
    private LocalDateTime dateShipment;
    private String address;
    private String reference;
    private String geoLocation;
    private String statusShipment;
}
