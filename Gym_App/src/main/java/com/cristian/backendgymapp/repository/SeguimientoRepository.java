package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {
    List<Seguimiento> findByClienteId(Long clienteId);
    List<Seguimiento> findByClienteIdOrderByFechaDesc(Long clienteId);
    List<Seguimiento> findByClienteIdAndFechaBetween(Long clienteId, String fechaInicio, String fechaFin);
} 