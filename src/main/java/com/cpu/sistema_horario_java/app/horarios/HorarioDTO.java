package com.cpu.sistema_horario_java.app.horarios;

import lombok.Data;

@Data
public class HorarioDTO {

    // data to build model
    private Long id;
    private Long cargaAcademica;
    private Long bloqueHorario;
    private Integer dia;

    // details
    private String asignatura;
    private String curso;
    private String docente;
    private String inicioBloque;
    private String finBloque;
    private Boolean estatus = true;

}