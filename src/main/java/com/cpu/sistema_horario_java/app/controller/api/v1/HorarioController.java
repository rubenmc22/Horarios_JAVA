package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.horario.HorarioDTO;
import com.cpu.sistema_horario_java.app.horario.HorarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @GetMapping("/api/v1/horarios/{id}")
    public HorarioDTO buscar(@PathVariable("{id}") Long id) {
        return service.buscar(id);
    }

    @GetMapping("/api/v1/horarios/curso/{id}")
    public List<HorarioDTO> horariosPorCurso(@PathVariable("{id}") Long id) {
        return service.horariosPorCurso(id);
    }

    @GetMapping("/api/v1/horarios")
    public List<HorarioDTO> listar() {
        return service.listar();
    }

    @PostMapping("/api/v1/horarios/generar")
    public ResponseEntity<?> generarHorarios() {
        service.generarHorarios();
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PostMapping("/api/v1/horarios/generar/curso/{id}")
    public ResponseEntity<?> generarHorariosPorCurso(@PathVariable("{id}") Long id) {
        service.generarHorariosPorCurso(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/api/v1/horarios/{id}")
    public HorarioDTO replaceEmployee(@PathVariable("{id}") Long id, @RequestBody HorarioDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/api/v1/horarios/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("{id}") Long id) {
        service.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
