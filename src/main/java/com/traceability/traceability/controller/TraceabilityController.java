package com.traceability.traceability.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import com.traceability.traceability.entiti.TraceabilityLot;
import com.traceability.traceability.repository.TraceabilityLotRepository;
import com.traceability.traceability.service.TraceabilityService;

@RestController
@RequestMapping("/api/lots")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TraceabilityController {

    private final TraceabilityService service;
    private final TraceabilityLotRepository traceabilitylotrepository ;
 
    @PostMapping
    public ResponseEntity<TraceabilityLot> create(@Valid @RequestBody TraceabilityLot lot) {
        return ResponseEntity.ok(service.createLot(lot));
    }

    @GetMapping("/{lotCode}")
    public ResponseEntity<TraceabilityLot> get(@PathVariable String lotCode) {
        return ResponseEntity.ok(service.getByLotCode(lotCode));
    }

    @GetMapping("/export")
public ResponseEntity<byte[]> exportToExcel() throws Exception {

    List<TraceabilityLot> lots = traceabilitylotrepository.findAll();

    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Lots");

    // Header
    String[] columns = {
            "ID", "Lot Code", "Product Name", "Variety",
            "Description", "Harvest Date", "Farm Name",
            "Farm Address", "Quantity", "Unit"
    };

    Row headerRow = sheet.createRow(0);

    for (int i = 0; i < columns.length; i++) {
        headerRow.createCell(i).setCellValue(columns[i]);
    }

    // Data
    int rowNum = 1;
    for (TraceabilityLot lot : lots) {

        Row row = sheet.createRow(rowNum++);

        row.createCell(0).setCellValue(lot.getId() != null ? lot.getId() : 0);
        row.createCell(1).setCellValue(lot.getLotCode());
        row.createCell(2).setCellValue(lot.getProductName());
        row.createCell(3).setCellValue(lot.getVariety());
        row.createCell(4).setCellValue(lot.getProductDescription());
        row.createCell(5).setCellValue(lot.getHarvestDate() != null ? lot.getHarvestDate().toString() : "");
        row.createCell(6).setCellValue(lot.getFarmName());
        row.createCell(7).setCellValue(lot.getFarmAddress());
        row.createCell(8).setCellValue(lot.getTotalQuantity() != null ? lot.getTotalQuantity() : 0);
        row.createCell(9).setCellValue(lot.getUnit());
    }

    // Auto-size columns
    for (int i = 0; i < columns.length; i++) {
        sheet.autoSizeColumn(i);
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    workbook.write(outputStream);
    workbook.close();

    String fileName = "lots_" + java.time.LocalDate.now() + ".xlsx";

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
            .contentType(MediaType.parseMediaType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(outputStream.toByteArray());
}
}
