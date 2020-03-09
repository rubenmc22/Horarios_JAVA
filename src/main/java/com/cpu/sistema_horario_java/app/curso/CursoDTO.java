package com.cpu.sistema_horario_java.app.curso;

import java.util.Set;

import lombok.Data;

@Data
public class CursoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long cargaAcademica;
    private Set<Integer> dias;
    private Boolean estatus = true;
}