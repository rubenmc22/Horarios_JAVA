package com.cpu.sistema_horario_java.app.profesor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.cpu.sistema_horario_java.app.controller.v1.api.ProfesorController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<ProfesorDTO, EntityModel<ProfesorDTO>> {

    @Override
    public EntityModel<ProfesorDTO> toModel(ProfesorDTO Profesor) {

        return new EntityModel<>(Profesor,
                linkTo(methodOn(ProfesorController.class).buscar(Profesor.getId())).withSelfRel(),
                linkTo(methodOn(ProfesorController.class).listar()).withRel("Profesors"));
    }
}