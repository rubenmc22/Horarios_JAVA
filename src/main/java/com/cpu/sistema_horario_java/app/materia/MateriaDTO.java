package com.cpu.sistema_horario_java.app.materia;

import lombok.Data;

@Data
public class MateriaDTO {
    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean estatus = true;
}