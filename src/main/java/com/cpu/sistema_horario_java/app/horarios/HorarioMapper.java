package com.cpu.sistema_horario_java.app.horarios;

import com.cpu.sistema_horario_java.app.asignaturas.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.cargas.CargaAcademica;
import com.cpu.sistema_horario_java.app.cargas.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.docentes.DocenteRepository;
import com.cpu.sistema_horario_java.app.horas.BloqueHorarioRepository;
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
    BloqueHorarioRepository pr;
    @Autowired
    CargaAcademicaRepository car;

    public Horario toModel(HorarioDTO dto) {
        Horario model = new Horario();

        model.setId(dto.getId());
        model.setDia(Dia.values()[dto.getDia()]);
        model.setBloqueHorario(dto.getBloqueHorario() == null ? null : pr.getOne(dto.getBloqueHorario()));
        model.setCargaAcademica(dto.getCargaAcademica() == null ? null : car.getOne(dto.getCargaAcademica()));
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Horario toModel(HorarioDTO dto, Horario model) {

        model.setDia(Dia.values()[dto.getDia()]);
        model.setBloqueHorario(dto.getBloqueHorario() == null ? null : pr.getOne(dto.getBloqueHorario()));
        model.setCargaAcademica(dto.getCargaAcademica() == null ? null : car.getOne(dto.getCargaAcademica()));
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public HorarioDTO toDTO(Horario model) {
        HorarioDTO dto = new HorarioDTO();

        CargaAcademica cargaAcademica = model.getCargaAcademica();

        // data to build model
        dto.setId(model.getId());
        dto.setCargaAcademica(cargaAcademica.getId());
        dto.setBloqueHorario(model.getBloqueHorario().getId());
        dto.setDia(model.getDia().getNumeroDia());

        // details
        dto.setAsignatura(cargaAcademica.getAsignatura().getNombre());
        dto.setCurso(cargaAcademica.getCurso().getNombre());
        dto.setDocente(
                cargaAcademica.getDocente().getNombre().concat(" ").concat(cargaAcademica.getDocente().getApellido()));
        dto.setInicioBloque(model.getBloqueHorario().getInicioBloque().toString());
        dto.setFinBloque(model.getBloqueHorario().getFinBloque().toString());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public HorarioDTO toDTO(Horario model, HorarioDTO dto) {
        CargaAcademica cargaAcademica = model.getCargaAcademica();

        // data to build model
        dto.setId(model.getId());
        dto.setCargaAcademica(cargaAcademica.getId());
        dto.setBloqueHorario(model.getBloqueHorario().getId());
        dto.setDia(model.getDia().getNumeroDia());

        // details
        dto.setAsignatura(cargaAcademica.getAsignatura().getNombre());
        dto.setCurso(cargaAcademica.getCurso().getNombre());
        dto.setDocente(
                cargaAcademica.getDocente().getNombre().concat(" ").concat(cargaAcademica.getDocente().getApellido()));
        dto.setInicioBloque(model.getBloqueHorario().getInicioBloque().toString());
        dto.setFinBloque(model.getBloqueHorario().getFinBloque().toString());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

}