package com.cpu.sistema_horario_java.app.horario;

import com.cpu.sistema_horario_java.app.util.Dia;

import lombok.Data;

@Data
public class HorarioDTO {
    private Long id;
    private String profesor;
    private String materia;
    private Dia dia;
    private String hora;
    private Boolean estatus = true;
}