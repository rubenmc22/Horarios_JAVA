package com.cpu.sistema_horario_java.app.materia;

import org.springframework.stereotype.Component;

@Component
public class MateriaMapper {

    public Materia toModel(MateriaDTO dto) {
        Materia model = new Materia();

        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Materia toModel(MateriaDTO dto, Materia model) {

        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public MateriaDTO toDTO(Materia model) {
        MateriaDTO dto = new MateriaDTO();

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public MateriaDTO toDTO(Materia model, MateriaDTO dto) {

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}