package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByClienteId(Long clienteId);
    List<Pago> findByClienteIdOrderByFechaPagoDesc(Long clienteId);
    List<Pago> findByClienteIdAndFechaPagoBetween(Long clienteId, String fechaInicio, String fechaFin);
    List<Pago> findByEstado(String estado);
} 