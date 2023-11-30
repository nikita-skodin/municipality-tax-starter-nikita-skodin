package com.testapp.municipalitytax.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TAXES")
public class TaxEntity {
    @Id
    private UUID id;
    private String municipality;
    private Double tax;
    private LocalDate startDate;
    private LocalDate endDate;

    public TaxEntity(UUID id, String municipality, Double tax, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.municipality = municipality;
        this.tax = tax;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TaxEntity() {

    }

    public UUID id() {
        return id;
    }

    public String municipality() {
        return municipality;
    }

    public Double tax() {
        return tax;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }
}
