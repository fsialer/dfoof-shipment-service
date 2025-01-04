package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.input.rest.models.request;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateShipmentRequest {
    @NotBlank(message = "Field address cannot be null or blank.")
    private String address;
    private String reference;
    private String geoLocation;
    @NotNull(message = "Field orders cannot be null.")
    private List<Long> orders;
    @NotNull(message = "Field dealerId cannot be null.")
    private Long dealerId;
}
