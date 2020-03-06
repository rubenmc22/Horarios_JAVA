package com.cpu.sistema_horario_java.app.curso;

import lombok.Data;

@Data
public class CursoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String cargaAcademica;
    private Boolean estatus = true;
}