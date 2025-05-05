package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    List<Ejercicio> findByNombreContainingIgnoreCase(String nombre);
    List<Ejercicio> findByGrupoMuscular(String grupoMuscular);
    List<Ejercicio> findByDificultad(String dificultad);
} 