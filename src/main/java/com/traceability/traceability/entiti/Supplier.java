package com.traceability.traceability.entiti;

import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String name;

  
    private String address;

    @NotNull
    private LocalDate receptionDate;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private TraceabilityLot lot;
}
