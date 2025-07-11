package com.backend.App_gym.repository;

import com.backend.App_gym.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Buscar por email
    Optional<Usuario> findByEmail(String email);
    
    // Buscar por username
    Optional<Usuario> findByUsername(String username);
    
    // Verificar si existe por email
    boolean existsByEmail(String email);
    
    // Verificar si existe por username
    boolean existsByUsername(String username);
}