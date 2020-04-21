package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.cursos.CursoDTO;
import com.cpu.sistema_horario_java.app.cursos.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping("/api/v1/cursos/{id}")
    public CursoDTO buscar(@PathVariable Long id) {
        CursoDTO dto = service.buscar(id);
        return dto;
    }

    @GetMapping("/api/v1/cursos")
    public List<CursoDTO> listar() {
        return service.listar();
    }

    @PostMapping("/api/v1/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public CursoDTO guardar(@RequestBody CursoDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/api/v1/cursos/{id}")
    public CursoDTO replaceEmployee(@PathVariable Long id, @RequestBody CursoDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/api/v1/cursos")
    public ResponseEntity<?> eliminar() {
        service.eliminar();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/v1/cursos/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
