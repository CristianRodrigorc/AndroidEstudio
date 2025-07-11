package com.backend.App_gym.repository;

import com.backend.App_gym.entity.UserData;
import com.backend.App_gym.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsuario(Usuario usuario);
}