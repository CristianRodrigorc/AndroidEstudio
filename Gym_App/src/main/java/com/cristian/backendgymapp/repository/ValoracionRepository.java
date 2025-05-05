package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    List<Valoracion> findByRutinaId(Long rutinaId);
    List<Valoracion> findByUsuarioId(Long usuarioId);
    Optional<Valoracion> findByRutinaIdAndUsuarioId(Long rutinaId, Long usuarioId);
    List<Valoracion> findByRutinaIdOrderByFechaCreacionDesc(Long rutinaId);
    List<Valoracion> findByRutinaIdAndFechaCreacionBetween(Long rutinaId, String fechaInicio, String fechaFin);
} 