package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;
import com.cpu.sistema_horario_java.app.docentes.DocenteDTO;
import com.cpu.sistema_horario_java.app.docentes.DocenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DocenteController {

    @Autowired
    private DocenteService service;

    @GetMapping("/api/v1/docentes/{id}")
    public DocenteDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping("/api/v1/docentes")
    public List<DocenteDTO> listar() {
        return service.listar();
    }

    @PostMapping("/api/v1/docentes")
    @ResponseStatus(HttpStatus.CREATED)
    public DocenteDTO guardar(@RequestBody DocenteDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/api/v1/docentes/{id}")
    public DocenteDTO replaceEmployee(@PathVariable Long id, @RequestBody DocenteDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/api/v1/docentes")
    public ResponseEntity<?> eliminar() {
        service.eliminar();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/v1/docentes/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
