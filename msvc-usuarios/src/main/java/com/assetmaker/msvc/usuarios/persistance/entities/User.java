package com.assetmaker.msvc.usuarios.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Entity(name = "\"user\"")
@Data
public class User {
    public enum RiskProfile {
        Conservative,
        Moderate,
        Risky
    }
    @Valid

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String first_name;
    @NotBlank
    private String last_names;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "risk_profile", nullable = false)
    private RiskProfile risk_profile;
}
