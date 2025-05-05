package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidaRepository extends JpaRepository<Medida, Long> {
    List<Medida> findBySeguimientoId(Long seguimientoId);
    List<Medida> findBySeguimientoClienteId(Long clienteId);
    List<Medida> findBySeguimientoClienteIdOrderBySeguimientoFechaDesc(Long clienteId);
} 