package com.cpu.sistema_horario_java.app.docente;

import java.util.List;

import lombok.Data;

@Data
public class DocenteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String telefono;
    private String correo;
    private List<Long> asignaturas;
    private Boolean estatus = true;
}