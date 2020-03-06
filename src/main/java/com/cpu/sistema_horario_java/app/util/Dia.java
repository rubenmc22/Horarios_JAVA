package com.cpu.sistema_horario_java.app.util;

public enum Dia {
    LUNES(1), MARTES(2), MIERCOLES(3), JUEVES(4), VIERNES(5), SABADO(6), DOMINGO(7);

    private final Integer NUMERO_DIA;
    private Boolean estatus = Boolean.TRUE;

    private Dia(Integer numeroDia) {
        this.NUMERO_DIA = numeroDia;
    }

    public Integer getNumeroDia() {
        return this.NUMERO_DIA;
    }

    public Boolean getEstatus() {
        return this.estatus;
    }
}
