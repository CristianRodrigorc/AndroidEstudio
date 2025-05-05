package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.ProgresoObjetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoObjetivoRepository extends JpaRepository<ProgresoObjetivo, Long> {
    List<ProgresoObjetivo> findByObjetivoId(Long objetivoId);
    List<ProgresoObjetivo> findByObjetivoClienteId(Long clienteId);
    List<ProgresoObjetivo> findByObjetivoIdOrderByFechaRegistroDesc(Long objetivoId);
    List<ProgresoObjetivo> findByObjetivoIdAndFechaRegistroBetween(
        Long objetivoId, String fechaInicio, String fechaFin);
} 