package com.cpu.sistema_horario_java.app.asignatura;

import java.util.Optional;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.docente.Docente;
import com.cpu.sistema_horario_java.app.asignatura_carga.AsignaturaCargaAcademica;
import com.cpu.sistema_horario_java.app.asignatura_carga.AsignaturaCargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;

import org.springframework.stereotype.Component;

@Component
public class AsignaturaMapper {

    private DocenteRepository dr;

    private AsignaturaCargaAcademicaRepository acar;

    public Asignatura toModel(AsignaturaDTO dto) {
        Asignatura model = new Asignatura();

        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setDocentes(dto.getDocentes().stream().map(idDocente -> dr.findById(idDocente))
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
        model.setCargas(dto.getCargas().stream().map(idCarga -> acar.findById(idCarga)).filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toList()));
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Asignatura toModel(AsignaturaDTO dto, Asignatura model) {

        model.setNombre(dto.getNombre());
        model.setDescripcion(dto.getDescripcion());
        model.setDocentes(dto.getDocentes().stream().map(idDocente -> dr.findById(idDocente))
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
        model.setCargas(dto.getCargas().stream().map(idCarga -> acar.findById(idCarga)).filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toList()));
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public AsignaturaDTO toDTO(Asignatura model) {
        AsignaturaDTO dto = new AsignaturaDTO();

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setDocentes(model.getDocentes().stream().map(Docente::getId).collect(Collectors.toList()));
        dto.setCargas(model.getCargas().stream().map(AsignaturaCargaAcademica::getId).collect(Collectors.toList()));
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public AsignaturaDTO toDTO(Asignatura model, AsignaturaDTO dto) {

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setDescripcion(model.getDescripcion());
        dto.setDocentes(model.getDocentes().stream().map(Docente::getId).collect(Collectors.toList()));
        dto.setCargas(model.getCargas().stream().map(AsignaturaCargaAcademica::getId).collect(Collectors.toList()));
        dto.setEstatus(model.getEstatus());

        return dto;
    }
}