package com.cpu.sistema_horario_java.app.profesor;

import lombok.Data;

@Data
public class ProfesorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String telefono;
    private String correo;
    private Boolean estatus = true;
}