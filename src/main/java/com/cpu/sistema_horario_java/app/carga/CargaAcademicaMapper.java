package com.cpu.sistema_horario_java.app.carga;

import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.asignatura.*;
import com.cpu.sistema_horario_java.app.asignatura_carga.AsignaturaCargaAcademica;
import com.cpu.sistema_horario_java.app.asignatura_carga.AsignaturaCargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.curso.Curso;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargaAcademicaMapper {

    @Autowired
    CursoRepository cr;

    @Autowired
    AsignaturaRepository mr;

    @Autowired
    AsignaturaCargaAcademicaRepository acar;

    public CargaAcademica toModel(CargaAcademicaDTO dto) {
        CargaAcademica model = new CargaAcademica();

        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setAsignaturas(acar.findAllById(dto.getAsignaturas()));
        model.setCursos(cr.findAllById(dto.getCursos()));
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public CargaAcademica toModel(CargaAcademicaDTO dto, CargaAcademica model) {
        model.setNombre(dto.getNombre());
        model.setAsignaturas(acar.findAllById(dto.getAsignaturas()));
        model.setCursos(cr.findAllById(dto.getCursos()));
        model.setEstatus(dto.getEstatus());
        return model;
    }

    public CargaAcademicaDTO toDTO(CargaAcademica model) {
        CargaAcademicaDTO dto = new CargaAcademicaDTO();

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setAsignaturas(
                model.getAsignaturas().stream().map(AsignaturaCargaAcademica::getId).collect(Collectors.toList()));
        dto.setCursos(model.getCursos().stream().map(Curso::getId).collect(Collectors.toList()));
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public CargaAcademicaDTO toDTO(CargaAcademica model, CargaAcademicaDTO dto) {

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setAsignaturas(
                model.getAsignaturas().stream().map(AsignaturaCargaAcademica::getId).collect(Collectors.toList()));
        dto.setCursos(model.getCursos().stream().map(Curso::getId).collect(Collectors.toList()));
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}