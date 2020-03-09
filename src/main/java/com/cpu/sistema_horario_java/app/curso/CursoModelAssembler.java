package com.cpu.sistema_horario_java.app.curso;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.cpu.sistema_horario_java.app.controller.api.v1.CursoController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<CursoDTO, EntityModel<CursoDTO>> {

    @Override
    public EntityModel<CursoDTO> toModel(CursoDTO dto) {

        return new EntityModel<>(dto,
                linkTo(methodOn(CursoController.class).buscar(dto.getId())).withSelfRel(),
                linkTo(methodOn(CursoController.class).listar()).withRel("cursos"));
    }
}