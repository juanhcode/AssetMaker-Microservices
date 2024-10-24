package com.assetmaker.msvc.portafolios.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity(name = "portfolio")
@Data
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private BigDecimal averageAnnualReturn;
    private BigDecimal portfolioPerformance;
    private BigDecimal standardDeviation;
    @Column(name = "id_user")
    private Integer idUser;
}
