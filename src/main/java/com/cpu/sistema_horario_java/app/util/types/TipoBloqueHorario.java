package com.cpu.sistema_horario_java.app.util.types;

import java.time.Duration;

public enum TipoBloqueHorario {
    APERTURA(Duration.ofMinutes(15)), RECREO(Duration.ofMinutes(20)), CLASES(Duration.ofMinutes(45));

    private final Duration duracion;

    private TipoBloqueHorario(Duration duracion) {
        this.duracion = duracion;
    }

    public Duration getDuracion() {
        return this.duracion;

    }
}
