package com.assetmaker.msvc.portafolios.persistance.entities;

import lombok.Data;

@Data
public class User {
    public enum RiskProfile {
        Conservative,
        Moderate,
        Risky
    }
    private Integer id;
    private String first_name;
    private String last_names;
    private String email;
    private String password;
    private RiskProfile risk_profile;
}
