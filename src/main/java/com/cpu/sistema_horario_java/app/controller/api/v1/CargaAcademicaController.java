package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;

import com.cpu.sistema_horario_java.app.cargas.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CargaAcademicaController {

    @Autowired
    private CargaAcademicaService service;

    @GetMapping("/api/v1/cargas_academicas/{id}")
    public CargaAcademicaDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping("/api/v1/cargas_academicas")
    public List<CargaAcademicaDTO> listar() {
        return service.listar();
    }

    @GetMapping("/api/v1/cargas_academicas/curso/{id}")
    public List<CargaAcademicaDTO> cargasPorCurso(@PathVariable Long id) {
        return service.cargasPorCurso(id);
    }

    @PostMapping("/api/v1/cargas_academicas")
    @ResponseStatus(HttpStatus.CREATED)
    public CargaAcademicaDTO guardar(@RequestBody CargaAcademicaDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/api/v1/cargas_academicas/{id}")
    public CargaAcademicaDTO replaceEmployee(@PathVariable Long id, @RequestBody CargaAcademicaDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/api/v1/cargas_academicas/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
