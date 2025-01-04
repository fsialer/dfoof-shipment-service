package com.fernando.ms.shipments.app.dfood_shipments_service.domain.models;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.enums.StatusShipmentEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shipment {
    private Long id;
    private LocalDateTime dateShipment;
    private String address;
    private String reference;
    private String geoLocation;
    private String statusShipment;
    private List<Order> orders;
    private List<Tracking> trackings;
    private Dealer dealer;
}
