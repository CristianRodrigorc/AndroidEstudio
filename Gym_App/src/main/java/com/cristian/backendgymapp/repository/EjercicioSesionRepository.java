package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.EjercicioSesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjercicioSesionRepository extends JpaRepository<EjercicioSesion, Long> {
    List<EjercicioSesion> findBySesionId(Long sesionId);
    List<EjercicioSesion> findByEjercicioId(Long ejercicioId);
    List<EjercicioSesion> findBySesionIdOrderByOrdenAsc(Long sesionId);
    void deleteBySesionId(Long sesionId);
} 