package com.traceability.traceability.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traceability.traceability.entiti.ProductIdentification;
import com.traceability.traceability.entiti.TraceabilityRecord;

public interface ProductIdentificationRepository 
        extends JpaRepository<ProductIdentification, Long> {

            ProductIdentification findByRecord(TraceabilityRecord record);
}