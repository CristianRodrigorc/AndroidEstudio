package com.backend.App_gym.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "datos_fisicos")
public class DatosFisicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "altura_cm")
    private Integer alturaCm;

    @Column(name = "peso_kg", precision = 5, scale = 2)
    private BigDecimal pesoKg;

    @Column(name = "frecuencia_actividad")
    private String frecuenciaActividad;

    @Column(name = "dias_entrenamiento_por_semana")
    private Integer diasEntrenamientoPorSemana;

    @Column(name = "problemas_salud")
    private String problemasSalud;

    @Column(name = "horario_preferido")
    private String horarioPreferido;

    @Column(name = "motivacion")
    private String motivacion;

    // Constructores
    public DatosFisicos() {}

    public DatosFisicos(Usuario usuario) {
        this.usuario = usuario;
    }

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

    public Integer getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(Integer alturaCm) {
        this.alturaCm = alturaCm;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getFrecuenciaActividad() {
        return frecuenciaActividad;
    }

    public void setFrecuenciaActividad(String frecuenciaActividad) {
        this.frecuenciaActividad = frecuenciaActividad;
    }

    public Integer getDiasEntrenamientoPorSemana() {
        return diasEntrenamientoPorSemana;
    }

    public void setDiasEntrenamientoPorSemana(Integer diasEntrenamientoPorSemana) {
        this.diasEntrenamientoPorSemana = diasEntrenamientoPorSemana;
    }

    public String getProblemasSalud() {
        return problemasSalud;
    }

    public void setProblemasSalud(String problemasSalud) {
        this.problemasSalud = problemasSalud;
    }

    public String getHorarioPreferido() {
        return horarioPreferido;
    }

    public void setHorarioPreferido(String horarioPreferido) {
        this.horarioPreferido = horarioPreferido;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }
} 