package com.cpu.sistema_horario_java.app.asignatura;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.cpu.sistema_horario_java.app.controller.api.v1.AsignaturaController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaModelAssembler
        implements RepresentationModelAssembler<AsignaturaDTO, EntityModel<AsignaturaDTO>> {

    @Override
    public EntityModel<AsignaturaDTO> toModel(AsignaturaDTO asignatura) {

        return new EntityModel<>(asignatura,
                linkTo(methodOn(AsignaturaController.class).buscar(asignatura.getId())).withSelfRel(),
                linkTo(methodOn(AsignaturaController.class).listar()).withRel("asignaturas"));
    }
}