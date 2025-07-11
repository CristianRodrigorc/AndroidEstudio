package com.backend.App_gym.repository;

import com.backend.App_gym.entity.DatosFisicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatosFisicosRepository extends JpaRepository<DatosFisicos, Long> {
    
    // Buscar todos los datos físicos de un usuario
    List<DatosFisicos> findByUsuarioId(Long usuarioId);
    
    // Buscar el último dato físico de un usuario
    Optional<DatosFisicos> findFirstByUsuarioIdOrderByIdDesc(Long usuarioId);
} 