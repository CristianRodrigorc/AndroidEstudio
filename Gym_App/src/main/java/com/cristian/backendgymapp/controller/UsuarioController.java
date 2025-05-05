package com.cristian.backendgymapp.controller;

import com.cristian.backendgymapp.entity.Usuario;
import com.cristian.backendgymapp.entity.UserData;
import com.cristian.backendgymapp.dto.UserDataRequest;
import com.cristian.backendgymapp.repository.UsuarioRepository;
import com.cristian.backendgymapp.repository.UserDataRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
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
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
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
    public ResponseEntity<?> registerUser(@Valid @RequestBody Usuario usuario) {
        try {
            logger.info("Recibida petición para registrar usuario: {}", usuario.getEmail());
            
            if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("El email ya está registrado");
            }

            if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
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

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            logger.info("Recibida petición para obtener usuario por email: {}", email);
            
            Usuario usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email: " + email));

            logger.info("Usuario encontrado exitosamente");
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            logger.error("Error al obtener usuario: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error al obtener el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUserData(@Valid @RequestBody UserDataRequest userDataRequest) {
        try {
            logger.info("Recibida petición para guardar datos del usuario: {}", userDataRequest.getUserId());
            
            if (userDataRequest.getUserId() == null) {
                logger.error("El userId no puede ser nulo");
                return ResponseEntity.badRequest().body("El userId no puede ser nulo");
            }

            Usuario usuario = usuarioRepository.findById(userDataRequest.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + userDataRequest.getUserId()));

            if (userDataRepository.findByUsuario_Id(usuario.getId_user()).isPresent()) {
                logger.warn("Ya existen datos para el usuario: {}", usuario.getId_user());
                return ResponseEntity.badRequest().body("Ya existen datos para este usuario");
            }

            UserData userData = new UserData();
            userData.setUsuario(usuario);
            userData.setSize(userDataRequest.getSize());
            userData.setWeight(userDataRequest.getWeight());
            userData.setActividadFisica(userDataRequest.getPhysicalActivity());
            userData.setDiasEntrenar(userDataRequest.getDaysTraining());
            userData.setProblemasSalud(userDataRequest.getHealthProblems());
            userData.setPreferenciaHorario(userDataRequest.getPreferenceSchedule());
            userData.setMotivacion(userDataRequest.getMotivation());

            userDataRepository.save(userData);
            logger.info("Datos del usuario guardados exitosamente");
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error al guardar datos del usuario: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error al guardar los datos: " + e.getMessage());
        }
    }

    @GetMapping("/data/{userId}")
    public ResponseEntity<?> getUserData(@PathVariable Long userId) {
        try {
            logger.info("Recibida petición para obtener datos del usuario: {}", userId);
            
            if (userId == null) {
                logger.error("El userId no puede ser nulo");
                return ResponseEntity.badRequest().body("El userId no puede ser nulo");
            }

            UserData userData = userDataRepository.findByUsuario_Id(userId)
                    .orElseThrow(() -> new RuntimeException("Datos del usuario no encontrados"));

            logger.info("Datos del usuario encontrados exitosamente");
            return ResponseEntity.ok(userData);
        } catch (Exception e) {
            logger.error("Error al obtener datos del usuario: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error al obtener los datos: " + e.getMessage());
        }
    }
} 