package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsuarioUsername(String username);
    List<Cliente> findByActivoTrue();
    List<Cliente> findByActivoFalse();
} 