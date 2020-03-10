package com.cpu.sistema_horario_java.app.horario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.carga.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;
import com.cpu.sistema_horario_java.app.horario.HorarioDTO.Detalle;
import com.cpu.sistema_horario_java.app.periodo.Period;
import com.cpu.sistema_horario_java.app.periodo.PeriodRepository;
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
    PeriodRepository pr;
    @Autowired
    CargaAcademicaRepository car;

    public Horario toModel(HorarioDTO dto) {
        Horario model = new Horario();

        Map<Dia, Period> periodoDia = new HashMap<>();
        List<Detalle> detalles = dto.getDetalles();

        for (HorarioDTO.Detalle detalle : detalles) {
            periodoDia.put(Dia.values()[detalle.getDia()],
                    detalle.getPeriodo() == null ? pr.getOne(2L) : pr.getOne(detalle.getPeriodo()));
        }

        model.setId(dto.getId());
        model.setCargaAcademica(dto.getCargaAcademica() == null ? null : car.getOne(dto.getCargaAcademica()));
        model.setPeriodoDia(periodoDia);
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public Horario toModel(HorarioDTO dto, Horario model) {

        Map<Dia, Period> periodoDia = new HashMap<>();
        List<Detalle> detalles = dto.getDetalles();

        for (HorarioDTO.Detalle detalle : detalles) {
            periodoDia.put(Dia.values()[detalle.getDia()],
                    detalle.getPeriodo() == null ? pr.getOne(2L) : pr.getOne(detalle.getPeriodo()));
        }

        model.setCargaAcademica(dto.getCargaAcademica() == null ? null : car.getOne(dto.getCargaAcademica()));
        model.setPeriodoDia(periodoDia);
        model.setEstatus(dto.getEstatus());

        return model;
    }

    public HorarioDTO toDTO(Horario model) {
        HorarioDTO dto = new HorarioDTO();

        CargaAcademica cargaAcademica = model.getCargaAcademica();

        dto.setId(model.getId());
        dto.setCargaAcademica(cargaAcademica.getId());
        dto.setAsignatura(cargaAcademica.getAsignatura().getId());
        dto.setDocente(cargaAcademica.getDocente().getId());
        dto.setCurso(cargaAcademica.getCurso().getId());
        dto.setCurso(model.getId());
        dto.setDetalles(getDetalles(model.getPeriodoDia()));
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    public HorarioDTO toDTO(Horario model, HorarioDTO dto) {
        CargaAcademica cargaAcademica = model.getCargaAcademica();

        dto.setId(model.getId());
        dto.setCargaAcademica(cargaAcademica.getId());
        dto.setAsignatura(cargaAcademica.getAsignatura().getId());
        dto.setDocente(cargaAcademica.getDocente().getId());
        dto.setCurso(cargaAcademica.getCurso().getId());
        dto.setCurso(model.getId());
        dto.setDetalles(getDetalles(model.getPeriodoDia()));
        dto.setEstatus(model.getEstatus());

        return dto;
    }

    private List<HorarioDTO.Detalle> getDetalles(Map<Dia, Period> datos) {
        List<HorarioDTO.Detalle> detalles = new ArrayList<HorarioDTO.Detalle>();

        Iterator<Entry<Dia, Period>> iterator = datos.entrySet().iterator();
        while (iterator.hasNext()) {
            HorarioDTO.Detalle detalle = new HorarioDTO.Detalle();
            Entry<Dia, Period> dato = iterator.next();

            detalle.setDia(dato.getKey().getNumeroDia());
            detalle.setPeriodo(dato.getValue().getId());
            detalle.setInicioPeriodo(dato.getValue().getInicioPeriodo().toString());
            detalle.setFinPeriodo(dato.getValue().getFinPeriodo().toString());

            detalles.add(detalle);
        }
        return detalles;
    }

}