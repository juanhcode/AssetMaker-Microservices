package com.assetmaker.msvc.activosportafolios.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "contains")
@Data
public class Contains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_portfolio")
    private Integer idPortfolio;
    @Column(name = "id_asset")
    private Integer idAsset;
}

