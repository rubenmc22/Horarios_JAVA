package com.cpu.sistema_horario_java.app.carga;

import lombok.Data;

@Data
public class CargaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String profesor;
    private String materia;
    private String curso;
    private Integer horas;
    private Boolean estatus = true;
}