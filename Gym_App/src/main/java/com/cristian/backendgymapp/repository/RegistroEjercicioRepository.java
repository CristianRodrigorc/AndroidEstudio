package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.RegistroEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroEjercicioRepository extends JpaRepository<RegistroEjercicio, Long> {
    List<RegistroEjercicio> findByEjercicioSesionId(Long ejercicioSesionId);
    List<RegistroEjercicio> findByEjercicioSesionSesionId(Long sesionId);
    List<RegistroEjercicio> findByEjercicioSesionSesionClienteId(Long clienteId);
    List<RegistroEjercicio> findByEjercicioSesionSesionClienteIdAndFechaBetween(
        Long clienteId, String fechaInicio, String fechaFin);
} 