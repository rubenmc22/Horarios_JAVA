package com.cpu.sistema_horario_java.app.asignaturas;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AsignaturaDTO {
    private Long id;
    private String nombre = "";
    private String descripcion = "";
    private List<Long> cargas = new ArrayList<>();
    private Boolean estatus = true;
}