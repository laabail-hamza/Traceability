package com.traceability.traceability.entiti;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lots")
@Getter
@Setter
public class TraceabilityLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String lotCode;

    @NotBlank
    private String productName;

    @NotBlank
    private String variety;

    @NotBlank
    private String productDescription; // entier / coupé / lavé

    @NotNull
    private LocalDate harvestDate;

    @NotBlank
    private String farmName;

    @NotBlank
    private String farmAddress;

    @NotNull
    private Double totalQuantity;

    @NotBlank
    private String unit; // kg / cartons
}