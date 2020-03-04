package com.cpu.sistema_horario_java.app.materia;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.cpu.sistema_horario_java.app.controller.v1.api.MateriaController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class MateriaModelAssembler implements RepresentationModelAssembler<MateriaDTO, EntityModel<MateriaDTO>> {

    @Override
    public EntityModel<MateriaDTO> toModel(MateriaDTO materia) {

        return new EntityModel<>(materia,
                linkTo(methodOn(MateriaController.class).buscar(materia.getId())).withSelfRel(),
                linkTo(methodOn(MateriaController.class).listar()).withRel("materias"));
    }
}