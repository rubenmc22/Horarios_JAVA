package com.cpu.sistema_horario_java.app.carga;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.cpu.sistema_horario_java.app.controller.v1.api.CargaController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CargaModelAssembler implements RepresentationModelAssembler<CargaDTO, EntityModel<CargaDTO>> {

    @Override
    public EntityModel<CargaDTO> toModel(CargaDTO dto) {

        return new EntityModel<>(dto,
                linkTo(methodOn(CargaController.class).buscar(dto.getId())).withSelfRel(),
                linkTo(methodOn(CargaController.class).listar()).withRel("cargas"));
    }
}