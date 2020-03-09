package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;
import com.cpu.sistema_horario_java.app.docente.DocenteDTO;
import com.cpu.sistema_horario_java.app.docente.DocenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/docentes")
public class DocenteController {

    @Autowired
    private DocenteService service;

    @GetMapping("/{id}")
    public DocenteDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping
    public List<DocenteDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public DocenteDTO guardar(@RequestBody DocenteDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public DocenteDTO replaceEmployee(@PathVariable Long id, @RequestBody DocenteDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
