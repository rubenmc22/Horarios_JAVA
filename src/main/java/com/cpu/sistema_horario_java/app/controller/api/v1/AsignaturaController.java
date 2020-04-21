package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.asignaturas.AsignaturaDTO;
import com.cpu.sistema_horario_java.app.asignaturas.AsignaturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AsignaturaController {

    @Autowired
    private AsignaturaService service;

    @GetMapping("/api/v1/asignaturas/{id}")
    public AsignaturaDTO buscar(@PathVariable Long id) {
        AsignaturaDTO Asignatura = service.buscar(id);
        return Asignatura;
    }

    @GetMapping("/api/v1/asignaturas")
    public List<AsignaturaDTO> listar() {
        return service.listar();
    }

    @PostMapping("/api/v1/asignaturas")
    @ResponseStatus(HttpStatus.CREATED)
    public AsignaturaDTO guardar(@RequestBody AsignaturaDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/api/v1/asignaturas/{id}")
    public AsignaturaDTO reemplazar(@PathVariable Long id, @RequestBody AsignaturaDTO dto) {
        return service.reemplazar(id, dto);
    }

    @DeleteMapping("/api/v1/asignaturas")
    public ResponseEntity<?> eliminar() {
        service.eliminar();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/v1/asignaturas/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
