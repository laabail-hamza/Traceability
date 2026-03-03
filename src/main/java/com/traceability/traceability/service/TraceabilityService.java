package com.traceability.traceability.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.traceability.traceability.DTO.TraceabilityDTO;
import com.traceability.traceability.entiti.AuditLog;
import com.traceability.traceability.entiti.ProductIdentification;
import com.traceability.traceability.entiti.ProductionInfo;
import com.traceability.traceability.entiti.ShippingEvent;
import com.traceability.traceability.entiti.TraceabilityRecord;
import com.traceability.traceability.repository.AuditLogRepository;
import com.traceability.traceability.repository.ProductIdentificationRepository;
import com.traceability.traceability.repository.ProductionInfoRepository;
import com.traceability.traceability.repository.ShippingEventRepository;
import com.traceability.traceability.repository.TraceabilityRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TraceabilityService {

    private final TraceabilityRecordRepository recordRepo;
    private final ProductIdentificationRepository productRepo;
    private final ProductionInfoRepository productionRepo;
    private final ShippingEventRepository shippingRepo;
    private final AuditLogRepository auditRepo;

    public TraceabilityRecord createRecord(TraceabilityDTO dto) {

        // 1️⃣ Génération TLC automatique
        long count = recordRepo.count() + 1;
        String year = String.valueOf(LocalDate.now().getYear());
        String tlc = "TLC-" + year + "-" + String.format("%05d", count);

        TraceabilityRecord record = new TraceabilityRecord();
        record.setTraceabilityLotCode(tlc);
        record.setStatus("DRAFT");
        record.setCreatedBy("admin");
        record.setCreatedAt(LocalDateTime.now());
        record.setVersion(1);

        recordRepo.save(record);

        // 2️⃣ Product
        ProductIdentification product = new ProductIdentification();
        product.setGtin(dto.getGtin());
        product.setDescription(dto.getDescription());
        product.setCommercialLot(dto.getCommercialLot());
        product.setSanitaryLot(dto.getSanitaryLot());
        product.setQuantity(dto.getQuantity());
        product.setUnit(dto.getUnit());
        product.setRecord(record);

        productRepo.save(product);

        // 3️⃣ Production
        ProductionInfo production = new ProductionInfo();
        production.setCountryOfOrigin(dto.getCountryOfOrigin());
        production.setProductionSiteName(dto.getProductionSiteName());
        production.setProductionSiteAddress(dto.getProductionSiteAddress());
        production.setSanitaryApprovalNumber(dto.getSanitaryApprovalNumber());
        production.setProductionDate(dto.getProductionDate());
        production.setHarvestDate(dto.getHarvestDate());
        production.setRecord(record);

        productionRepo.save(production);

        // 4️⃣ Shipping
        ShippingEvent shipping = new ShippingEvent();
        shipping.setShipperName(dto.getShipperName());
        shipping.setShipperAddress(dto.getShipperAddress());
        shipping.setShipperGLN(dto.getShipperGLN());
        shipping.setShippingDateTime(dto.getShippingDateTime());
        shipping.setTransportMode(dto.getTransportMode());
        shipping.setTransportTemperature(dto.getTransportTemperature());
        shipping.setRecord(record);

        shippingRepo.save(shipping);

        // 5️⃣ Audit
        AuditLog log = new AuditLog();
        log.setAction("CREATE");
        log.setUsername("admin");
        log.setActionDate(LocalDateTime.now());
        log.setDetails("Created record with TLC: " + tlc);
        auditRepo.save(log);

        return record;
    }
}