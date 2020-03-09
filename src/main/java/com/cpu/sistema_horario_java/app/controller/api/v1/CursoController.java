package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.curso.CursoDTO;
import com.cpu.sistema_horario_java.app.curso.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService service;


    @GetMapping("/{id}")
    public CursoDTO buscar(@PathVariable Long id) {
        CursoDTO dto = service.buscar(id);
        return dto;
    }

    @GetMapping
    public List <CursoDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public CursoDTO guardar(@RequestBody CursoDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public CursoDTO replaceEmployee(@PathVariable Long id, @RequestBody CursoDTO dto) {
            return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
