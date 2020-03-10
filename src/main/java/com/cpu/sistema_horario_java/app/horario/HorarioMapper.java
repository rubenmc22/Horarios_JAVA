package com.cpu.sistema_horario_java.app.horario;

import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;
import com.cpu.sistema_horario_java.app.util.types.Dia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    @Autowired
    DocenteRepository dr;
    @Autowired
    AsignaturaRepository ar;
    @Autowired
    CursoRepository cr;

    public Horario toModel(HorarioDTO dto) {
        Horario model = new Horario();

        model.setId(dto.getId());
        model.setDia(Dia.values()[dto.getDia()]);
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Horario toModel(HorarioDTO dto, Horario model) {

        model.setDia(Dia.values()[dto.getDia()]);
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public HorarioDTO toDTO(Horario model) {
        HorarioDTO dto = new HorarioDTO();

        dto.setId(model.getId());
        dto.setDia(model.getDia().getNumeroDia());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public HorarioDTO toDTO(Horario model, HorarioDTO dto) {

        dto.setId(model.getId());
        dto.setDia(model.getDia().getNumeroDia());
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}