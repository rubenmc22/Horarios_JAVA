package com.cpu.sistema_horario_java.app.cargas;

import com.cpu.sistema_horario_java.app.asignaturas.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.cursos.CursoRepository;
import com.cpu.sistema_horario_java.app.docentes.DocenteRepository;
import com.cpu.sistema_horario_java.app.util.types.Estatus;

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

        // model data
        model.setId(dto.getId());
        model.setAsignatura(dto.getAsignatura() == null ? null : ar.getOne(dto.getAsignatura()));
        model.setCurso(dto.getCurso() == null ? null : cr.getOne(dto.getCurso()));
        model.setDocente(dto.getDocente() == null ? null : dr.getOne(dto.getDocente()));
        model.setHoras(dto.getHoras());
        model.setEstatus(Estatus.getEstatus(dto.getEstatus()));
        return model;
    }

    public CargaAcademica toModel(CargaAcademicaDTO dto, CargaAcademica model) {

        // model data
        model.setAsignatura(dto.getAsignatura() == null ? null : ar.getOne(dto.getAsignatura()));
        model.setCurso(dto.getCurso() == null ? null : cr.getOne(dto.getCurso()));
        model.setDocente(dto.getDocente() == null ? null : dr.getOne(dto.getDocente()));
        model.setHoras(dto.getHoras());
        model.setEstatus(Estatus.getEstatus(dto.getEstatus()));
        return model;
    }

    public CargaAcademicaDTO toDTO(CargaAcademica model) {
        CargaAcademicaDTO dto = new CargaAcademicaDTO();

        // model data
        dto.setId(model.getId());
        dto.setAsignatura(model.getAsignatura() == null ? null : model.getAsignatura().getId());
        dto.setCurso(model.getCurso() == null ? null : model.getCurso().getId());
        dto.setDocente(model.getDocente() == null ? null : model.getDocente().getId());
        dto.setHoras(model.getHoras());
        dto.setEstatus(model.getEstatus().getEstatus());

        // details
        dto.setDetalleAsignatura(model.getAsignatura() == null ? null : model.getAsignatura().getNombre());
        dto.setDetalleCurso(model.getCurso() == null ? null : model.getCurso().getNombre());
        dto.setDetalleDocente(model.getDocente() == null ? null
                : model.getDocente().getNombre().concat(" ").concat(model.getDocente().getApellido()));

        return dto;
    }

    public CargaAcademicaDTO toDTO(CargaAcademica model, CargaAcademicaDTO dto) {

        // model data
        dto.setId(model.getId());
        dto.setAsignatura(model.getAsignatura() == null ? null : model.getAsignatura().getId());
        dto.setCurso(model.getCurso() == null ? null : model.getCurso().getId());
        dto.setDocente(model.getDocente() == null ? null : model.getDocente().getId());
        dto.setHoras(model.getHoras());
        dto.setEstatus(model.getEstatus().getEstatus());

        // details
        dto.setDetalleAsignatura(model.getAsignatura() == null ? null : model.getAsignatura().getNombre());
        dto.setDetalleCurso(model.getCurso() == null ? null : model.getCurso().getNombre());
        dto.setDetalleDocente(model.getDocente() == null ? null
                : model.getDocente().getNombre().concat(" ").concat(model.getDocente().getApellido()));

        return dto;
    }
}