package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models;

import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Dealer;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Order;
import com.fernando.ms.shipments.app.dfood_shipments_service.domain.models.Tracking;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @JoinColumn(name = "shipment_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipmentOrder> shipmentOrderList;

    @JoinColumn(name = "shipment_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackingEntity> trackingList=new ArrayList<>();

    @JoinColumn(name = "shipment_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ShipmentDealer shipmentDealer;

    public void addShipmentOrder(List<Long> orderIds){
        if (this.shipmentOrderList == null) {
            this.shipmentOrderList = new ArrayList<>();
        }
        orderIds.forEach(orderId -> this.shipmentOrderList.add(ShipmentOrder
                .builder()
                .orderId(orderId)
                .build()));
//        this.shipmentOrderList.add(ShipmentOrder
//                .builder()
//                .orderId(shipmentOrder.getId())
//                .build());
    }

    public void addTracking(){
        if (this.trackingList == null) {
            this.trackingList = new ArrayList<>();
        }
        this.trackingList.add(TrackingEntity
                .builder()
                .status(this.getStatusShipment())
                .createdAt(LocalDateTime.now())
                .build());
    }

    public void setShipmentDealer(Long dealerId){
        this.shipmentDealer = ShipmentDealer
                .builder()
                .dealerId(dealerId)
                .build();
    }




}
