package com.traceability.traceability.service;

import org.springframework.stereotype.Service;

import com.traceability.traceability.entiti.TraceabilityLot;
import com.traceability.traceability.repository.TraceabilityLotRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TraceabilityService {

    private final TraceabilityLotRepository lotRepository;

    public TraceabilityLot createLot(TraceabilityLot lot) {

        if(lotRepository.findByLotCode(lot.getLotCode()).isPresent()) {
            throw new RuntimeException("Lot code already exists");
        }

        return lotRepository.save(lot);
    }

    public TraceabilityLot getByLotCode(String lotCode) {
        return lotRepository.findByLotCode(lotCode)
                .orElseThrow(() -> new RuntimeException("Lot not found"));
    }
}