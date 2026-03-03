package com.traceability.traceability.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traceability.traceability.entiti.ProductionInfo;
import com.traceability.traceability.entiti.TraceabilityRecord;

public interface ProductionInfoRepository extends JpaRepository<ProductionInfo, Long> {

    ProductionInfo findByRecord(TraceabilityRecord record);
}