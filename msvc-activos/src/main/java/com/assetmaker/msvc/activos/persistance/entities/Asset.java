package com.assetmaker.msvc.activos.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Entity (name = "asset")
@Data
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String symbol;
    private BigDecimal fiveYearPerformance;
    private BigDecimal fiveYearRisk;
    private BigDecimal maximumYield;
    private BigDecimal minimumYield;
}
