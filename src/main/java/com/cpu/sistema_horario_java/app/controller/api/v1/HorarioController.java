package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.horarios.HorarioDTO;
import com.cpu.sistema_horario_java.app.horarios.HorarioService;

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
    public HorarioDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping("/api/v1/horarios/curso/{id}")
    public List<HorarioDTO> horariosPorCurso(@PathVariable Long id) {
        return service.horariosPorCurso(id);
    }

    @GetMapping("/api/v1/horarios/docente/{id}")
    public List<HorarioDTO> horariosPorDocente(@PathVariable Long id) {
        return service.horariosPorDocente(id);
    }

    @GetMapping("/api/v1/horarios")
    public List<HorarioDTO> listar() {
        return service.listar();
    }

    @PostMapping("/api/v1/horarios/generar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> generarHorarios() {
        service.generarHorarios();
        return ResponseEntity.ok().build();

    }

    @PostMapping("/api/v1/horarios/generar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<HorarioDTO> generarHorariosPorCurso(@PathVariable Long id) {
        service.generarHorariosPorCurso(id);
        return service.horariosPorCurso(id);

    }

    @PutMapping("/api/v1/horarios/{id}")
    public HorarioDTO reemplazar(@PathVariable Long id, @RequestBody HorarioDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/api/v1/horarios")
    public ResponseEntity<?> eliminarTodo() {
        service.eliminarTodo();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/v1/horarios/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
