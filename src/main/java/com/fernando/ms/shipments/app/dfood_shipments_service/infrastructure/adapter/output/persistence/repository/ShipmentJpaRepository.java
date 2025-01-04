package com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.repository;

import com.fernando.ms.shipments.app.dfood_shipments_service.infrastructure.adapter.output.persistence.models.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentJpaRepository extends JpaRepository<ShipmentEntity,Long> {
}
