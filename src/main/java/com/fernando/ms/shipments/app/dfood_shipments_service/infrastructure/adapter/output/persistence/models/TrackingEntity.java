package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="trackings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrackingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String details;
    private LocalDateTime createdAt;
}
