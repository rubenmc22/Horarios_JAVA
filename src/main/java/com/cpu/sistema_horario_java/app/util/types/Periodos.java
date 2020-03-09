package com.cpu.sistema_horario_java.app.util.types;

import java.time.Duration;

public enum Periodos {
    APERTURA(Duration.ofMinutes(15)), RECREO(Duration.ofMinutes(20)), BLOQUE(Duration.ofMinutes(45));

    private final Duration duracion;

    private Periodos(Duration duracion) {
        this.duracion = duracion;
    }

    public Duration getDuracion() {
        return this.duracion;

    }
}
