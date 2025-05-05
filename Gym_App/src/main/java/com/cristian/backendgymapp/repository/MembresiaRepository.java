package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
    List<Membresia> findByClienteId(Long clienteId);
    List<Membresia> findByClienteIdAndActivaTrue(Long clienteId);
    List<Membresia> findByClienteIdAndActivaFalse(Long clienteId);
    List<Membresia> findByFechaInicioBetween(String fechaInicio, String fechaFin);
    List<Membresia> findByFechaFinBetween(String fechaInicio, String fechaFin);
} 