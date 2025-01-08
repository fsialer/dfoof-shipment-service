package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.restclient.models.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealerClientResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String numberLicense;
    private String numberDocument;
    private String expirationDateLicense;
}
