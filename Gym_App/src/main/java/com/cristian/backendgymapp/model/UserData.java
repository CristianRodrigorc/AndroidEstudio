package com.cristian.backendgymapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private Usuario usuario;
    
    private Integer size;
    private Integer weight;
    
    @Column(name = "actividad_fisica")
    private String actividadFisica;
    
    @Column(name = "dias_entrenar")
    private String diasEntrenar;
    
    @Column(name = "problemas_salud")
    private String problemasSalud;
    
    @Column(name = "preferencia_horario")
    private String preferenciaHorario;
    
    private String motivacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public String getDiasEntrenar() {
        return diasEntrenar;
    }

    public void setDiasEntrenar(String diasEntrenar) {
        this.diasEntrenar = diasEntrenar;
    }

    public String getProblemasSalud() {
        return problemasSalud;
    }

    public void setProblemasSalud(String problemasSalud) {
        this.problemasSalud = problemasSalud;
    }

    public String getPreferenciaHorario() {
        return preferenciaHorario;
    }

    public void setPreferenciaHorario(String preferenciaHorario) {
        this.preferenciaHorario = preferenciaHorario;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }
} 