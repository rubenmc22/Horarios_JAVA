package com.cpu.sistema_horario_java.app.horario;

import java.util.List;

import lombok.Data;

@Data
public class HorarioDTO {
    private Long id;
    // Datos para armar el modelo
    private Long cargaAcademica;
    private List<Detalle> detalles;
    // **************************
    private Long asignatura;
    private Long curso;
    private Long docente;
    private Boolean estatus = true;

    @Data
    static class Detalle {
        Long periodo;
        Integer dia;
        String inicioPeriodo;
        String finPeriodo;
    }
}