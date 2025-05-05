package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.SesionEntrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionEntrenamientoRepository extends JpaRepository<SesionEntrenamiento, Long> {
    List<SesionEntrenamiento> findByClienteId(Long clienteId);
    List<SesionEntrenamiento> findByEntrenadorId(Long entrenadorId);
    List<SesionEntrenamiento> findByClienteIdAndFechaBetween(Long clienteId, String fechaInicio, String fechaFin);
    List<SesionEntrenamiento> findByEntrenadorIdAndFechaBetween(Long entrenadorId, String fechaInicio, String fechaFin);
    List<SesionEntrenamiento> findByClienteIdAndEstado(Long clienteId, String estado);
    List<SesionEntrenamiento> findByEntrenadorIdAndEstado(Long entrenadorId, String estado);
} 