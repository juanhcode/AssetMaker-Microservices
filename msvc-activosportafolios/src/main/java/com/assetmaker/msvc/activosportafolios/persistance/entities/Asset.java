package com.assetmaker.msvc.activosportafolios.persistance.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Asset {

    private Integer id;
    private String name;
    private String symbol;
    private BigDecimal fiveYearPerformance;
    private BigDecimal fiveYearRisk;
    private BigDecimal maximumYield;
    private BigDecimal minimumYield;
}
