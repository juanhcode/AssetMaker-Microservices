package com.assetmaker.msvc.auth.persistance.models;

public class Usuario {
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

    public Usuario() {
    }

    public Usuario(Integer id, String first_name, String last_names, String email, String password, RiskProfile risk_profile) {
        this.id = id;
        this.first_name = first_name;
        this.last_names = last_names;
        this.email = email;
        this.password = password;
        this.risk_profile = risk_profile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_names='" + last_names + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", risk_profile=" + risk_profile +
                '}';
    }
}