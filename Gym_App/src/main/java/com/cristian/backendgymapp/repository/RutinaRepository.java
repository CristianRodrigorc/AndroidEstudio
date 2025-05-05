package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {
    List<Rutina> findByClienteId(Long clienteId);
    List<Rutina> findByEntrenadorId(Long entrenadorId);
    List<Rutina> findByClienteIdAndActivaTrue(Long clienteId);
    List<Rutina> findByClienteIdAndActivaFalse(Long clienteId);
} 