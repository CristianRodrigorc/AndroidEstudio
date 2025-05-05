package com.backend.App_gym.controller;

import com.backend.App_gym.entity.Usuario;
import com.backend.App_gym.entity.UserData;
import com.backend.App_gym.dto.UserDataRequest;
import com.backend.App_gym.repository.UsuarioRepository;
import com.backend.App_gym.repository.UserDataRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "API para la gestión de usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @GetMapping("/check-email/{email}")
    @Operation(summary = "Verificar disponibilidad de email", description = "Verifica si un email ya está registrado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Email disponible"),
        @ApiResponse(responseCode = "400", description = "Email ya registrado")
    })
    public ResponseEntity<Boolean> checkEmail(@Parameter(description = "Email a verificar") @PathVariable String email) {
        try {
            logger.info("Verificando email: {}", email);
            boolean exists = usuarioRepository.existsByEmail(email);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            logger.error("Error al verificar email: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/check-username/{username}")
    @Operation(summary = "Verificar disponibilidad de username", description = "Verifica si un nombre de usuario ya está en uso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Username disponible"),
        @ApiResponse(responseCode = "400", description = "Username ya en uso")
    })
    public ResponseEntity<Boolean> checkUsername(@Parameter(description = "Username a verificar") @PathVariable String username) {
        try {
            logger.info("Verificando username: {}", username);
            boolean exists = usuarioRepository.existsByUsername(username);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            logger.error("Error al verificar username: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar nuevo usuario", description = "Crea un nuevo usuario en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos o email/username ya en uso")
    })
    public ResponseEntity<?> registerUser(@Parameter(description = "Datos del usuario a registrar") @Valid @RequestBody Usuario usuario) {
        try {
            logger.info("Recibida petición para registrar usuario: {}", usuario.getEmail());

            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                return ResponseEntity.badRequest().body("El email ya está registrado");
            }

            if (usuarioRepository.existsByUsername(usuario.getUsername())) {
                return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso");
            }

            Usuario savedUsuario = usuarioRepository.save(usuario);
            logger.info("Usuario registrado exitosamente con ID: {}", savedUsuario.getId_user());

            return ResponseEntity.ok(savedUsuario);
        } catch (Exception e) {
            logger.error("Error al registrar usuario: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error al registrar el usuario: " + e.getMessage());
        }
    }
} 