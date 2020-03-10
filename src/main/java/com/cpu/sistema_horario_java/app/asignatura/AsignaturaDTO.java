package com.cpu.sistema_horario_java.app.asignatura;

import java.util.List;

import lombok.Data;

@Data
public class AsignaturaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Long> cargas;
    private Boolean estatus = true;
}