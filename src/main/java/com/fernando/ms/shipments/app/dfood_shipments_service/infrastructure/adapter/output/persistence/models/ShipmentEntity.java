package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Dealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Order;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Tracking;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="shipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateShipment;
    private String address;
    private String reference;
    private String geoLocation;
    private String statusShipment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
