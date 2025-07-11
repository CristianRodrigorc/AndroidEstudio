package com.backend.App_gym.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class DatosFisicosRequest {
    
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;
    
    private Integer alturaCm;
    
    private BigDecimal pesoKg;
    
    @Size(max = 50, message = "La frecuencia de actividad no puede tener más de 50 caracteres")
    private String frecuenciaActividad;
    
    private Integer diasEntrenamientoPorSemana;
    
    @Size(max = 500, message = "Los problemas de salud no pueden tener más de 500 caracteres")
    private String problemasSalud;
    
    @Size(max = 20, message = "El horario preferido no puede tener más de 20 caracteres")
    private String horarioPreferido;
    
    @Size(max = 500, message = "La motivación no puede tener más de 500 caracteres")
    private String motivacion;

    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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