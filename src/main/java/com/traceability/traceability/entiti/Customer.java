package com.traceability.traceability.entiti;

import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 
    private String name;

 
    private String address;

    @NotNull
    private LocalDate shippingDate;

    @NotNull
    private Double quantityShipped;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private TraceabilityLot lot;
}