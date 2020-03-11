package com.cpu.sistema_horario_java.app.horario;

import lombok.Data;

@Data
public class HorarioDTO {

    // data to build model
    private Long id;
    private Long cargaAcademica;
    private Long periodo;
    private Integer dia;

    // details
    private String asignatura;
    private String curso;
    private String docente;
    private String inicioPeriodo;
    private String finPeriodo;

    private Boolean estatus = true;

}