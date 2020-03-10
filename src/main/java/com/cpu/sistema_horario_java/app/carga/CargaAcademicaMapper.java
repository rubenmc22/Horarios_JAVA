package com.cpu.sistema_horario_java.app.carga;

import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargaAcademicaMapper {

    @Autowired
    private AsignaturaRepository ar;

    @Autowired
    private CursoRepository cr;

    @Autowired
    private DocenteRepository dr;

    public CargaAcademica toModel(CargaAcademicaDTO dto) {
        CargaAcademica model = new CargaAcademica();

        model.setId(dto.getId());
        model.setAsignatura(dto.getAsignatura() == null ? null : ar.getOne(dto.getAsignatura()));
        model.setCurso(dto.getCurso() == null ? null : cr.getOne(dto.getCurso()));
        model.setDocente(dto.getDocente() == null ? null : dr.getOne(dto.getDocente()));
        model.setHoras(dto.getHoras());
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public CargaAcademica toModel(CargaAcademicaDTO dto, CargaAcademica model) {

        model.setAsignatura(dto.getAsignatura() == null ? null : ar.getOne(dto.getAsignatura()));
        model.setCurso(dto.getCurso() == null ? null : cr.getOne(dto.getCurso()));
        model.setDocente(dto.getDocente() == null ? null : dr.getOne(dto.getDocente()));
        model.setHoras(dto.getHoras());
        return model;
    }

    public CargaAcademicaDTO toDTO(CargaAcademica model) {
        CargaAcademicaDTO dto = new CargaAcademicaDTO();

        dto.setId(model.getId());
        dto.setAsignatura(model.getAsignatura() == null ? null : model.getAsignatura().getId());
        dto.setCurso(model.getCurso() == null ? null : model.getCurso().getId());
        dto.setDocente(model.getDocente() == null ? null : model.getDocente().getId());
        dto.setHoras(model.getHoras());
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public CargaAcademicaDTO toDTO(CargaAcademica model, CargaAcademicaDTO dto) {

        dto.setId(model.getId());
        dto.setAsignatura(model.getAsignatura() == null ? null : model.getAsignatura().getId());
        dto.setCurso(model.getCurso() == null ? null : model.getCurso().getId());
        dto.setDocente(model.getDocente() == null ? null : model.getDocente().getId());
        dto.setHoras(model.getHoras());
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}