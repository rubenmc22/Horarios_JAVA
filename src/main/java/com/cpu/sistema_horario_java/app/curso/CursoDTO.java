package com.cpu.sistema_horario_java.app.curso;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class CursoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Set<Integer> dias = new HashSet<>();
    private Boolean estatus = true;
}