package com.cpu.sistema_horario_java.app.carga;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CargaAcademicaDTO {
    private Long id;
    private Long asignatura;
    private Long curso;
    private Long docente;
    private Integer horas;
    private String estatus = "PENDIENTE";

}