package com.backend.App_gym.controller;

import com.backend.App_gym.dto.DatosFisicosRequest;
import com.backend.App_gym.entity.DatosFisicos;
import com.backend.App_gym.entity.Usuario;
import com.backend.App_gym.repository.DatosFisicosRepository;
import com.backend.App_gym.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/datos-fisicos")
@CrossOrigin(origins = "*")
public class DatosFisicosController {

    @Autowired
    private DatosFisicosRepository datosFisicosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los datos físicos
    @GetMapping
    public ResponseEntity<List<DatosFisicos>> getAllDatosFisicos() {
        List<DatosFisicos> datosFisicos = datosFisicosRepository.findAll();
        return ResponseEntity.ok(datosFisicos);
    }

    // Obtener datos físicos por ID
    @GetMapping("/{id}")
    public ResponseEntity<DatosFisicos> getDatosFisicosById(@PathVariable Long id) {
        Optional<DatosFisicos> datosFisicos = datosFisicosRepository.findById(id);
        
        if (datosFisicos.isPresent()) {
            return ResponseEntity.ok(datosFisicos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener datos físicos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DatosFisicos>> getDatosFisicosByUsuario(@PathVariable Long usuarioId) {
        List<DatosFisicos> datosFisicos = datosFisicosRepository.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(datosFisicos);
    }

    // Obtener el último dato físico de un usuario
    @GetMapping("/usuario/{usuarioId}/ultimo")
    public ResponseEntity<DatosFisicos> getUltimoDatoFisico(@PathVariable Long usuarioId) {
        Optional<DatosFisicos> datosFisicos = datosFisicosRepository.findFirstByUsuarioIdOrderByIdDesc(usuarioId);
        
        if (datosFisicos.isPresent()) {
            return ResponseEntity.ok(datosFisicos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevos datos físicos
    @PostMapping
    public ResponseEntity<DatosFisicos> createDatosFisicos(@Valid @RequestBody DatosFisicosRequest request) {
        // Verificar que el usuario existe
        Optional<Usuario> usuario = usuarioRepository.findById(request.getUsuarioId());
        if (!usuario.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        // Crear nuevos datos físicos
        DatosFisicos datosFisicos = new DatosFisicos();
        datosFisicos.setUsuario(usuario.get());
        datosFisicos.setAlturaCm(request.getAlturaCm());
        datosFisicos.setPesoKg(request.getPesoKg());
        datosFisicos.setFrecuenciaActividad(request.getFrecuenciaActividad());
        datosFisicos.setDiasEntrenamientoPorSemana(request.getDiasEntrenamientoPorSemana());
        datosFisicos.setProblemasSalud(request.getProblemasSalud());
        datosFisicos.setHorarioPreferido(request.getHorarioPreferido());
        datosFisicos.setMotivacion(request.getMotivacion());

        DatosFisicos savedDatosFisicos = datosFisicosRepository.save(datosFisicos);
        return ResponseEntity.ok(savedDatosFisicos);
    }

    // Actualizar datos físicos
    @PutMapping("/{id}")
    public ResponseEntity<DatosFisicos> updateDatosFisicos(@PathVariable Long id, @Valid @RequestBody DatosFisicosRequest request) {
        Optional<DatosFisicos> existingDatosFisicos = datosFisicosRepository.findById(id);
        
        if (existingDatosFisicos.isPresent()) {
            DatosFisicos datosFisicos = existingDatosFisicos.get();
            datosFisicos.setAlturaCm(request.getAlturaCm());
            datosFisicos.setPesoKg(request.getPesoKg());
            datosFisicos.setFrecuenciaActividad(request.getFrecuenciaActividad());
            datosFisicos.setDiasEntrenamientoPorSemana(request.getDiasEntrenamientoPorSemana());
            datosFisicos.setProblemasSalud(request.getProblemasSalud());
            datosFisicos.setHorarioPreferido(request.getHorarioPreferido());
            datosFisicos.setMotivacion(request.getMotivacion());

            DatosFisicos updatedDatosFisicos = datosFisicosRepository.save(datosFisicos);
            return ResponseEntity.ok(updatedDatosFisicos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar datos físicos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDatosFisicos(@PathVariable Long id) {
        if (datosFisicosRepository.existsById(id)) {
            datosFisicosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
} 