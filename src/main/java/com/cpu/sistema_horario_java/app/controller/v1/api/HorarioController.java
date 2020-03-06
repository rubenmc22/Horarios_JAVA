package com.cpu.sistema_horario_java.app.controller.v1.api;

import java.util.List;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.horario.HorarioDTO;
import com.cpu.sistema_horario_java.app.horario.HorarioModelAssembler;
import com.cpu.sistema_horario_java.app.horario.HorarioService;

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
@RequestMapping("/api/v1/horarios")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @Autowired
    private HorarioModelAssembler assembler;

    @GetMapping("/{id}")
    public EntityModel<HorarioDTO> buscar(@PathVariable Long id) {

        HorarioDTO dto = service.buscar(id);

        return assembler.toModel(dto);
    }

    @GetMapping
    public CollectionModel<EntityModel<HorarioDTO>> listar() {

        List<EntityModel<HorarioDTO>> resourceList = service.listar().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(resourceList, linkTo(methodOn(HorarioController.class).listar()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody HorarioDTO dto) {
        EntityModel<HorarioDTO> resource = assembler.toModel(service.guardar(dto));

        return ResponseEntity.created(resource.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceEmployee(@PathVariable Long id, @RequestBody HorarioDTO dto) {
        EntityModel<HorarioDTO> resource = assembler.toModel(service.actualizar(id, dto));

        return ResponseEntity.created(resource.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

}