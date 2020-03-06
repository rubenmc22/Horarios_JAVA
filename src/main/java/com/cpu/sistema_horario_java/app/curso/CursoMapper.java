package com.cpu.sistema_horario_java.app.curso;

import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso toModel(CursoDTO dto) {
        Curso model = new Curso();

        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setCargaAcademica(dto.getCargaAcademica());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Curso toModel(CursoDTO dto, Curso model) {

        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setCargaAcademica(dto.getCargaAcademica());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public CursoDTO toDTO(Curso model) {
        CursoDTO dto = new CursoDTO();

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setCargaAcademica(model.getCargaAcademica());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public CursoDTO toDTO(Curso model, CursoDTO dto) {

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setCargaAcademica(model.getCargaAcademica());
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}