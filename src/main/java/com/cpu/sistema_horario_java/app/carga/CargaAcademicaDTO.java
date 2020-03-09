package com.cpu.sistema_horario_java.app.carga;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CargaAcademicaDTO {
    private Long id;
    private String nombre;
    private List<Long> asignaturas;
    private List<Long> cursos;
    private Boolean estatus = true;

}