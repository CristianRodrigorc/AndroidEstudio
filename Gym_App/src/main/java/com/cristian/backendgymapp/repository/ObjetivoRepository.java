package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {
    List<Objetivo> findByClienteId(Long clienteId);
    List<Objetivo> findByClienteIdAndCompletadoFalse(Long clienteId);
    List<Objetivo> findByClienteIdAndCompletadoTrue(Long clienteId);
    List<Objetivo> findByClienteIdAndFechaObjetivoBetween(Long clienteId, String fechaInicio, String fechaFin);
    List<Objetivo> findByClienteIdOrderByFechaObjetivoAsc(Long clienteId);
} 