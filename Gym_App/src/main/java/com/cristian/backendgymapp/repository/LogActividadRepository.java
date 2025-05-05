package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.LogActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogActividadRepository extends JpaRepository<LogActividad, Long> {
    List<LogActividad> findByUsuarioId(Long usuarioId);
    List<LogActividad> findByUsuarioIdOrderByFechaHoraDesc(Long usuarioId);
    List<LogActividad> findByUsuarioIdAndFechaHoraBetween(Long usuarioId, String fechaInicio, String fechaFin);
    List<LogActividad> findByTipoAccion(String tipoAccion);
    List<LogActividad> findByFechaHoraBetween(String fechaInicio, String fechaFin);
} 