package com.cpu.sistema_horario_java.app.cargas;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CargaAcademicaDTO {
    // data to build model
    private Long id;
    private Long asignatura;
    private Long curso;
    private Long docente;
    private Integer horas = 1;
    private String estatus = "PENDIENTE";

    // details
    private String detalleAsignatura;
    private String detalleCurso;
    private String detalleDocente;

    // manual reservation system data
    private List<Reserva> reservas = new ArrayList<>();
}