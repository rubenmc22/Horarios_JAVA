package com.cpu.sistema_horario_java.app.horario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.cpu.sistema_horario_java.app.controller.api.v1.HorarioController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class HorarioModelAssembler implements RepresentationModelAssembler<HorarioDTO, EntityModel<HorarioDTO>> {

    @Override
    public EntityModel<HorarioDTO> toModel(HorarioDTO dto) {

        return new EntityModel<>(dto,
                linkTo(methodOn(HorarioController.class).buscar(dto.getId())).withSelfRel(),
                linkTo(methodOn(HorarioController.class).listar()).withRel("horarios"));
    }
}