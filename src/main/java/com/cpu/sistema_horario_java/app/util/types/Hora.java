package com.cpu.sistema_horario_java.app.util.types;


public enum Hora {
    LUNES(1), MARTES(2), MIERCOLES(3), JUEVES(4), VIERNES(5);

    private final Integer NUMERO_DIA;

    private Hora(Integer numeroDia) {
        this.NUMERO_DIA = numeroDia;
    }

    public Integer getNumeroDia() {
        return this.NUMERO_DIA;
    }
}
