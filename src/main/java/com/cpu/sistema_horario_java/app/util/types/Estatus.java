package com.cpu.sistema_horario_java.app.util.types;

public enum Estatus {
    PENDIENTE("PENDIENTE"), PROGRAMADA("PROGRAMADA"), RESERVADA("RESERVADA");

    private final String ESTATUS;

    private Estatus(final String ESTATUS) {
        this.ESTATUS = ESTATUS;
    }

    public String getEstatus() {
        return this.ESTATUS;
    }

    public static Estatus getEstatus(String estatus) {

        final String ESTATUS = estatus.toUpperCase();
        switch (ESTATUS) {
            case "PROGRAMADA":
                return Estatus.PROGRAMADA;
            case "RESERVADA":
                return Estatus.RESERVADA;
            default:
                return Estatus.PENDIENTE;
        }

    }

}