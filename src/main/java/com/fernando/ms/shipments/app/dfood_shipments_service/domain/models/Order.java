package com.fernando.ms.shipments.app.dfood_shipments_service.domain.models;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Long id;
    private LocalDate dateOrder;
    private Double totalAmount;
    private String statusOrder;
    private List<Product> products;
}
