package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name="shipment_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private Long orderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShipmentOrder that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId);
    }
}
