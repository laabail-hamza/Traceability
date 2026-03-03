package com.traceability.traceability.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traceability.traceability.entiti.TraceabilityRecord;

public interface TraceabilityRecordRepository extends JpaRepository<TraceabilityRecord, Long> {
    long count();
}