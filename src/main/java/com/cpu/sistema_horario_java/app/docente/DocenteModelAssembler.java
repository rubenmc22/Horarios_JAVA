package com.cpu.sistema_horario_java.app.docente;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.cpu.sistema_horario_java.app.controller.api.v1.DocenteController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DocenteModelAssembler implements RepresentationModelAssembler<DocenteDTO, EntityModel<DocenteDTO>> {

    @Override
    public EntityModel<DocenteDTO> toModel(DocenteDTO docente) {

        return new EntityModel<>(docente,
                linkTo(methodOn(DocenteController.class).buscar(docente.getId())).withSelfRel(),
                linkTo(methodOn(DocenteController.class).listar()).withRel("docentes"));
    }
}