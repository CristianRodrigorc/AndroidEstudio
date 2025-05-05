package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    List<Token> findByUsuarioId(Long usuarioId);
    List<Token> findByUsuarioIdAndExpiradoFalseAndRevocadoFalse(Long usuarioId);
    List<Token> findByUsuarioIdAndTipoToken(String usuarioId, String tipoToken);
} 