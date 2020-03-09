package com.cpu.sistema_horario_java.app.controller.api.v1;

import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.asignatura.AsignaturaDTO;
import com.cpu.sistema_horario_java.app.asignatura.AsignaturaModelAssembler;
import com.cpu.sistema_horario_java.app.asignatura.AsignaturaService;

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
@RequestMapping("/api/v1/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService service;

    @Autowired
    private AsignaturaModelAssembler assembler;

    @GetMapping("/{id}")
    public EntityModel<AsignaturaDTO> buscar(@PathVariable Long id) {

        AsignaturaDTO Asignatura = service.buscar(id);

        return assembler.toModel(Asignatura);
    }

    @GetMapping
    public CollectionModel<EntityModel<AsignaturaDTO>> listar() {

        List<EntityModel<AsignaturaDTO>> Asignaturas = service.listar().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(Asignaturas, linkTo(methodOn(AsignaturaController.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody AsignaturaDTO dto) {
        EntityModel<AsignaturaDTO> Asignatura = assembler.toModel(service.guardar(dto));

        return ResponseEntity.created(Asignatura.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(Asignatura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> reemplazar(@PathVariable Long id, @RequestBody AsignaturaDTO dto) {
        EntityModel<AsignaturaDTO> Asignatura = assembler.toModel(service.reemplazar(id, dto));

        return ResponseEntity.created(Asignatura.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(Asignatura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}