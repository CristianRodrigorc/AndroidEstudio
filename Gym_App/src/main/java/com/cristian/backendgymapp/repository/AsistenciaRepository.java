package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByClienteId(Long clienteId);
    List<Asistencia> findByClienteIdOrderByFechaDesc(Long clienteId);
    List<Asistencia> findByClienteIdAndFechaBetween(Long clienteId, String fechaInicio, String fechaFin);
    List<Asistencia> findByFechaBetween(String fechaInicio, String fechaFin);
} 