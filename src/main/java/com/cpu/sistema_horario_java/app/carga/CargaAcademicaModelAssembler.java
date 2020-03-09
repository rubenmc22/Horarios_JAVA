package com.cpu.sistema_horario_java.app.carga;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.cpu.sistema_horario_java.app.controller.api.v1.CargaAcademicaController;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CargaAcademicaModelAssembler implements RepresentationModelAssembler<CargaAcademicaDTO, EntityModel<CargaAcademicaDTO>> {

    @Override
    public EntityModel<CargaAcademicaDTO> toModel(CargaAcademicaDTO dto) {

        return new EntityModel<>(dto,
                linkTo(methodOn(CargaAcademicaController.class).buscar(dto.getId())).withSelfRel(),
                linkTo(methodOn(CargaAcademicaController.class).listar()).withRel("cargas_academicas"));
    }
}