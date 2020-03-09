package com.cpu.sistema_horario_java.app.horario;

import java.util.Set;

import lombok.Data;

@Data
public class HorarioDTO {
    private Long id;
    private Long docente;
    private Long asignatura;
    private Long curso;
    private Integer dia;
    private Set<String> bloqueHorario;
    private String tipoBloqueHorario;
    private Integer duracion;
    private Boolean estatus = true;
}