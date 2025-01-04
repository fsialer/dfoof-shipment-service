package com.fernando.ms.shipments.app.dfood_shipments_service.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dealer {
    private Long id;
    private String name;
    private String phone;
}
