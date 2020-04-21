package com.cpu.sistema_horario_java.app.cursos;

import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.cargas.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.util.types.Dia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    @Autowired
    CargaAcademicaRepository car;

    public Curso toModel(CursoDTO dto) {
        Curso model = new Curso();

        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setDias(dto.getDias().stream().map(d -> Dia.values()[d]).collect(Collectors.toSet()));
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Curso toModel(CursoDTO dto, Curso model) {

        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setDias(dto.getDias().stream().map(d -> Dia.values()[d]).collect(Collectors.toSet()));
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public CursoDTO toDTO(Curso model) {
        CursoDTO dto = new CursoDTO();

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setDias(model.getDias().stream().map(d -> d.ordinal()).collect(Collectors.toSet()));
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public CursoDTO toDTO(Curso model, CursoDTO dto) {

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setDias(model.getDias().stream().map(d -> d.ordinal()).collect(Collectors.toSet()));
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}