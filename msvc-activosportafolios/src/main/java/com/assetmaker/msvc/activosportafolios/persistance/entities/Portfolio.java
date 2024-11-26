package com.assetmaker.msvc.activosportafolios.persistance.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Portfolio {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal averageAnnualReturn;
    private BigDecimal portfolioPerformance;
    private BigDecimal standardDeviation;
    private Integer idUser;
}
