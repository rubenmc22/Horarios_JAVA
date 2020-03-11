package com.cpu.sistema_horario_java.app.horario;

import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.carga.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;
import com.cpu.sistema_horario_java.app.periodo.PeriodoRepository;
import com.cpu.sistema_horario_java.app.util.types.Dia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HorarioMapper {

    @Autowired
    DocenteRepository dr;
    @Autowired
    AsignaturaRepository ar;
    @Autowired
    PeriodoRepository pr;
    @Autowired
    CargaAcademicaRepository car;

    public Horario toModel(HorarioDTO dto) {
        Horario model = new Horario();

        model.setId(dto.getId());
        model.setDia(Dia.values()[dto.getDia()]);
        model.setPeriodo(dto.getPeriodo() == null ? null : pr.getOne(dto.getPeriodo()));
        model.setCargaAcademica(dto.getCargaAcademica() == null ? null : car.getOne(dto.getCargaAcademica()));
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Horario toModel(HorarioDTO dto, Horario model) {

        model.setDia(Dia.values()[dto.getDia()]);
        model.setPeriodo(dto.getPeriodo() == null ? null : pr.getOne(dto.getPeriodo()));
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
        dto.setPeriodo(model.getPeriodo().getId());
        dto.setDia(model.getDia().getNumeroDia());

        // details
        dto.setAsignatura(cargaAcademica.getAsignatura().getNombre());
        dto.setCurso(cargaAcademica.getCurso().getNombre());
        dto.setDocente(
                cargaAcademica.getDocente().getNombre().concat(" ").concat(cargaAcademica.getDocente().getApellido()));
        dto.setInicioPeriodo(model.getPeriodo().getInicioPeriodo().toString());
        dto.setFinPeriodo(model.getPeriodo().getFinPeriodo().toString());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public HorarioDTO toDTO(Horario model, HorarioDTO dto) {
        CargaAcademica cargaAcademica = model.getCargaAcademica();

        // data to build model
        dto.setId(model.getId());
        dto.setCargaAcademica(cargaAcademica.getId());
        dto.setPeriodo(model.getPeriodo().getId());
        dto.setDia(model.getDia().getNumeroDia());

        // details
        dto.setAsignatura(cargaAcademica.getAsignatura().getNombre());
        dto.setCurso(cargaAcademica.getCurso().getNombre());
        dto.setDocente(
                cargaAcademica.getDocente().getNombre().concat(" ").concat(cargaAcademica.getDocente().getApellido()));
        dto.setInicioPeriodo(model.getPeriodo().getInicioPeriodo().toString());
        dto.setFinPeriodo(model.getPeriodo().getFinPeriodo().toString());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

}