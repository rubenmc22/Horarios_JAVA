package com.cpu.sistema_horario_java.app.carga;

import org.springframework.stereotype.Component;

@Component
public class CargaMapper {

    public Carga toModel(CargaDTO dto) {
        Carga model = new Carga();

        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setProfesor(dto.getProfesor());
        model.setMateria(dto.getMateria());
        model.setCurso(dto.getCurso());
        model.setHoras(dto.getHoras());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Carga toModel(CargaDTO dto, Carga model) {

        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setProfesor(dto.getProfesor());
        model.setMateria(dto.getMateria());
        model.setCurso(dto.getCurso());
        model.setHoras(dto.getHoras());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public CargaDTO toDTO(Carga model) {
        CargaDTO dto = new CargaDTO();

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setProfesor(model.getProfesor());
        dto.setMateria(model.getMateria());
        dto.setCurso(model.getCurso());
        dto.setHoras(model.getHoras());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public CargaDTO toDTO(Carga model, CargaDTO dto) {

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setProfesor(model.getProfesor());
        dto.setMateria(model.getMateria());
        dto.setCurso(model.getCurso());
        dto.setHoras(model.getHoras());
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}