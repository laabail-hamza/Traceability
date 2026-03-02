package com.traceability.traceability.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import com.traceability.traceability.entiti.TraceabilityLot;
import com.traceability.traceability.service.TraceabilityService;

@RestController
@RequestMapping("/api/lots")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TraceabilityController {

    private final TraceabilityService service;

    @PostMapping
    public ResponseEntity<TraceabilityLot> create(@Valid @RequestBody TraceabilityLot lot) {
        return ResponseEntity.ok(service.createLot(lot));
    }

    @GetMapping("/{lotCode}")
    public ResponseEntity<TraceabilityLot> get(@PathVariable String lotCode) {
        return ResponseEntity.ok(service.getByLotCode(lotCode));
    }
}
