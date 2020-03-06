package com.cpu.sistema_horario_java.app.horario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.cpu.sistema_horario_java.app.controller.v1.api.HorarioController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class HorarioModelAssembler implements RepresentationModelAssembler<HorarioDTO, EntityModel<HorarioDTO>> {

    @Override
    public EntityModel<HorarioDTO> toModel(HorarioDTO dto) {

        return new EntityModel<>(dto,
                linkTo(methodOn(HorarioController.class).buscar(dto.getId())).withSelfRel(),
                linkTo(methodOn(HorarioController.class).listar()).withRel("Horarios"));
    }
}