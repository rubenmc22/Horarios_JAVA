package com.cpu.sistema_horario_java.app.controller.v1.api;

import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.profesor.ProfesorDTO;
import com.cpu.sistema_horario_java.app.profesor.ProfesorModelAssembler;
import com.cpu.sistema_horario_java.app.profesor.ProfesorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService service;

    @Autowired
    private ProfesorModelAssembler assembler;

    @GetMapping("/{id}")
    public EntityModel<ProfesorDTO> buscar(@PathVariable Long id) {

        ProfesorDTO dto = service.buscar(id);

        return assembler.toModel(dto);
    }

    @GetMapping
    public CollectionModel<EntityModel<ProfesorDTO>> listar() {

        List<EntityModel<ProfesorDTO>> resourceList = service.listar().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(resourceList, linkTo(methodOn(ProfesorController.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody ProfesorDTO dto) {
        EntityModel<ProfesorDTO> resource = assembler.toModel(service.guardar(dto));

        return ResponseEntity.created(resource.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceEmployee(@PathVariable Long id, @RequestBody ProfesorDTO dto) {
        EntityModel<ProfesorDTO> resource = assembler.toModel(service.actualizar(id, dto));

        return ResponseEntity.created(resource.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}