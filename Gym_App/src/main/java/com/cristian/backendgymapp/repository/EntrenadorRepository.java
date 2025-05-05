package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
    Optional<Entrenador> findByUsuarioUsername(String username);
    List<Entrenador> findByActivoTrue();
    List<Entrenador> findByActivoFalse();
} 