package com.cpu.sistema_horario_java.app.util.types;

public enum BloqueHorario {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10), XI(11), XII(12);

    private final Integer NUMERO_BLOQUE;

    private BloqueHorario(Integer numeroBloque) {
        this.NUMERO_BLOQUE = numeroBloque;
    }

    public Integer getNumeroBloque() {
        return this.NUMERO_BLOQUE;
    }

    public String getNombreBloque() {
        return this.toString();
    }
}
