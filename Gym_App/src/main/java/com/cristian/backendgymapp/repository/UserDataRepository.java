package com.cristian.backendgymapp.repository;

import com.cristian.backendgymapp.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsuario_Id(Long usuarioId);
} 