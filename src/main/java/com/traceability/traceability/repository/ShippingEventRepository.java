package com.traceability.traceability.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traceability.traceability.entiti.ShippingEvent;
import com.traceability.traceability.entiti.TraceabilityRecord;

public interface ShippingEventRepository extends JpaRepository<ShippingEvent, Long> {

    ShippingEvent findFirstByRecord(TraceabilityRecord record);
}
