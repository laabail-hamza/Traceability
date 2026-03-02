package com.traceability.traceability.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traceability.traceability.entiti.TraceabilityLot;

public interface TraceabilityLotRepository extends JpaRepository<TraceabilityLot, Long> {
    Optional<TraceabilityLot> findByLotCode(String lotCode);
}