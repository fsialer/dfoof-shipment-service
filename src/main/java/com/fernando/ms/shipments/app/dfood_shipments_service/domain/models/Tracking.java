package com.fernando.ms.shipments.app.dfood_shipments_service.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tracking {
    private Long id;
    private String status;
    private String details;
}
