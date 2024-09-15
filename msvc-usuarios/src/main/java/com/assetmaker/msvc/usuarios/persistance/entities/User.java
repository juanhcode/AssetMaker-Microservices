package com.assetmaker.msvc.usuarios.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String first_name;
    @NotBlank
    @NotNull
    private String last_names;
    @NotBlank
    @Email
    @NotNull
    @Column(unique = true)
    private String email;
    @NotBlank
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "risk_profile", nullable = false)
    @NotNull
    private RiskProfile risk_profile;
}
