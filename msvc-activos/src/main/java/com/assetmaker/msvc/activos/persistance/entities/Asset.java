package com.assetmaker.msvc.activos.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity (name = "asset")
@Data
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String AssetType;
}
