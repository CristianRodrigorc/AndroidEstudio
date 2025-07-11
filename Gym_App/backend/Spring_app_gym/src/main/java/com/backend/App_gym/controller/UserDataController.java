package com.backend.App_gym.controller;

import com.backend.App_gym.entity.UserData;
import com.backend.App_gym.repository.UserDataRepository;
import com.backend.App_gym.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userdata")
@Tag(name = "Datos de Usuario", description = "API para la gestión de datos específicos de usuarios")
public class UserDataController {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Operation(summary = "Obtener todos los datos de usuario", description = "Retorna una lista de todos los datos de usuario registrados")
    @ApiResponse(responseCode = "200", description = "Lista de datos de usuario obtenida exitosamente")
    @SecurityRequirement(name = "basicAuth")
    public List<UserData> getAllUserData() {
        return userDataRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener datos de usuario por ID", description = "Retorna los datos específicos de un usuario basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos de usuario encontrados"),
        @ApiResponse(responseCode = "404", description = "Datos de usuario no encontrados")
    })
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<UserData> getUserDataById(@Parameter(description = "ID de los datos de usuario a buscar") @PathVariable Long id) {
        Optional<UserData> userData = userDataRepository.findById(id);
        return userData.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nuevos datos de usuario", description = "Crea nuevos datos para un usuario en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos de usuario creados exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<UserData> createUserData(@Parameter(description = "Datos del usuario a crear") @RequestBody UserData userData) {
        UserData savedUserData = userDataRepository.save(userData);
        return ResponseEntity.ok(savedUserData);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de usuario", description = "Actualiza los datos específicos de un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos de usuario actualizados exitosamente"),
        @ApiResponse(responseCode = "404", description = "Datos de usuario no encontrados")
    })
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<UserData> updateUserData(
            @Parameter(description = "ID de los datos a actualizar") @PathVariable Long id,
            @Parameter(description = "Nuevos datos del usuario") @RequestBody UserData userDataDetails) {
        return userDataRepository.findById(id)
                .map(userData -> {
                    userData.setSize(userDataDetails.getSize());
                    userData.setWeight(userDataDetails.getWeight());
                    userData.setActividadFisica(userDataDetails.getActividadFisica());
                    userData.setDiasEntrenar(userDataDetails.getDiasEntrenar());
                    userData.setProblemasSalud(userDataDetails.getProblemasSalud());
                    userData.setPreferenciaHorario(userDataDetails.getPreferenciaHorario());
                    userData.setMotivacion(userDataDetails.getMotivacion());
                    return ResponseEntity.ok(userDataRepository.save(userData));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar datos de usuario", description = "Elimina los datos específicos de un usuario del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos de usuario eliminados exitosamente"),
        @ApiResponse(responseCode = "404", description = "Datos de usuario no encontrados")
    })
    @SecurityRequirement(name = "basicAuth")
    public ResponseEntity<Void> deleteUserData(@Parameter(description = "ID de los datos a eliminar") @PathVariable Long id) {
        if (userDataRepository.existsById(id)) {
            userDataRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
} 