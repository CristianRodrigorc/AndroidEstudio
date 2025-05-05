package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByRemitenteIdOrDestinatarioIdOrderByFechaEnvioDesc(Long remitenteId, Long destinatarioId);
    List<Mensaje> findByRemitenteIdAndDestinatarioIdOrderByFechaEnvioAsc(Long remitenteId, Long destinatarioId);
    List<Mensaje> findByDestinatarioIdAndLeidoFalse(Long destinatarioId);
    List<Mensaje> findByRemitenteIdAndDestinatarioIdAndFechaEnvioBetween(
        Long remitenteId, Long destinatarioId, String fechaInicio, String fechaFin);
} 