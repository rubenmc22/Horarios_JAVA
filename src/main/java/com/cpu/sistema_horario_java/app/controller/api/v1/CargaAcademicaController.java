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
    public EntityModel<CargaAcademicaDTO> buscar(@PathVariable Long id) {

        CargaAcademicaDTO dto = service.buscar(id);

        return assembler.toModel(dto);
    }

    @GetMapping
    public CollectionModel<EntityModel<CargaAcademicaDTO>> listar() {

        List<EntityModel<CargaAcademicaDTO>> resourceList = service.listar().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(resourceList,
                linkTo(methodOn(CargaAcademicaController.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody CargaAcademicaDTO dto) {
        EntityModel<CargaAcademicaDTO> resource = assembler.toModel(service.guardar(dto));

        return ResponseEntity.created(resource.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceEmployee(@PathVariable Long id, @RequestBody CargaAcademicaDTO dto) {
        EntityModel<CargaAcademicaDTO> resource = assembler.toModel(service.actualizar(id, dto));

        return ResponseEntity.created(resource.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}
