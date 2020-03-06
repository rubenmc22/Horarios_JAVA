package com.cpu.sistema_horario_java.app.horario;

import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    public Horario toModel(HorarioDTO dto) {
        Horario model = new Horario();

        model.setId(dto.getId());
        model.setProfesor(dto.getProfesor());
        model.setMateria(dto.getMateria());
        model.setHora(dto.getHora());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Horario toModel(HorarioDTO dto, Horario model) {

        model.setProfesor(dto.getProfesor());
        model.setMateria(dto.getMateria());
        model.setHora(dto.getHora());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public HorarioDTO toDTO(Horario model) {
        HorarioDTO dto = new HorarioDTO();

        dto.setId(model.getId());
        dto.setProfesor(model.getProfesor());
        dto.setMateria(model.getMateria());
        dto.setHora(model.getHora());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public HorarioDTO toDTO(Horario model, HorarioDTO dto) {

        dto.setId(model.getId());
        dto.setProfesor(model.getProfesor());
        dto.setMateria(model.getMateria());
        dto.setHora(model.getHora());
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}