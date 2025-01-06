package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name="shipment_dealer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dealer_id")
    private Long dealerId;
    @OneToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private ShipmentEntity shipment; // Relación inversa

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShipmentDealer that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(dealerId, that.dealerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dealerId);
    }
}
