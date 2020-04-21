package com.cpu.sistema_horario_java.app.cargas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    private Integer dia;
    private Long bloqueHorario;
}