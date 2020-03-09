package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.carga.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/cargas_academicas")
public class CargaAcademicaController {

    @Autowired
    private CargaAcademicaService service;

    @Autowired
    private CargaAcademicaModelAssembler assembler;

    @GetMapping("/{id}")
    public CargaAcademicaDTO buscar(@PathVariable Long id) {
        CargaAcademicaDTO dto = service.buscar(id);
        return service.buscar(id);
    }

    @GetMapping
    public List <CargaAcademicaDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public CargaAcademicaDTO guardar(@RequestBody CargaAcademicaDTO dto) {
           return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public CargaAcademicaDTO replaceEmployee(@PathVariable Long id, @RequestBody CargaAcademicaDTO dto) {

        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
