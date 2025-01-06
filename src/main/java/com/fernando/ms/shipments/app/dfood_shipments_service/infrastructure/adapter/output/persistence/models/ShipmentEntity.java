package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    //@CreatedDate
    private LocalDateTime createdAt;
    //@LastModifiedDate
    private LocalDateTime updatedAt;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipment_id",referencedColumnName = "id")
    private List<ShipmentOrder> shipmentOrderList;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipment_id",referencedColumnName = "id")
    private List<TrackingEntity> trackings=new ArrayList<>();


    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "shipment_id",referencedColumnName = "id")
    private ShipmentDealer shipmentDealer;

    public void addShipmentOrder(List<Long> orderIds){
        if (this.shipmentOrderList == null) {
            this.shipmentOrderList = new ArrayList<>();
        }
        orderIds.forEach(orderId -> this.shipmentOrderList.add(ShipmentOrder
                .builder()
                .orderId(orderId)
                .build()));
    }

    public void addTracking(){
        if (this.trackings == null) {
            this.trackings = new ArrayList<>();
        }
        this.trackings.add(TrackingEntity
                .builder()
                .status(this.getStatusShipment())
                .createdAt(LocalDateTime.now())
                .build());
    }

    public void setShipmentDealerId(Long dealerId){
        this.shipmentDealer = ShipmentDealer
                .builder()
                .dealerId(dealerId)
                .shipment(this)
                .build();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
