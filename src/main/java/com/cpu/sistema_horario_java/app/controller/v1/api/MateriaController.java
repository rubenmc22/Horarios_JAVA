package com.cpu.sistema_horario_java.app.controller.v1.api;

import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.materia.MateriaDTO;
import com.cpu.sistema_horario_java.app.materia.MateriaModelAssembler;
import com.cpu.sistema_horario_java.app.materia.MateriaService;

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
@RequestMapping("/api/v1/materias")
public class MateriaController {

    @Autowired
    private MateriaService service;

    @Autowired
    private MateriaModelAssembler assembler;

    @GetMapping("/{id}")
    public EntityModel<MateriaDTO> buscar(@PathVariable Long id) {

        MateriaDTO materia = service.buscar(id);

        return assembler.toModel(materia);
    }

    @GetMapping
    public CollectionModel<EntityModel<MateriaDTO>> listar() {

        List<EntityModel<MateriaDTO>> materias = service.listar().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(materias, linkTo(methodOn(MateriaController.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody MateriaDTO dto) {
        EntityModel<MateriaDTO> materia = assembler.toModel(service.guardar(dto));

        return ResponseEntity.created(materia.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(materia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> reemplazar(@PathVariable Long id, @RequestBody MateriaDTO dto) {
        EntityModel<MateriaDTO> materia = assembler.toModel(service.reemplazar(id, dto));

        return ResponseEntity.created(materia.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(materia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}