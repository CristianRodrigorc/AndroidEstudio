package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.EjercicioRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjercicioRutinaRepository extends JpaRepository<EjercicioRutina, Long> {
    List<EjercicioRutina> findByRutinaId(Long rutinaId);
    List<EjercicioRutina> findByEjercicioId(Long ejercicioId);
    void deleteByRutinaId(Long rutinaId);
} 