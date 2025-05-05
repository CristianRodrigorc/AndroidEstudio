package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByRutinaId(Long rutinaId);
    List<Comentario> findByUsuarioId(Long usuarioId);
    List<Comentario> findByRutinaIdOrderByFechaCreacionDesc(Long rutinaId);
    List<Comentario> findByRutinaIdAndFechaCreacionBetween(Long rutinaId, String fechaInicio, String fechaFin);
} 