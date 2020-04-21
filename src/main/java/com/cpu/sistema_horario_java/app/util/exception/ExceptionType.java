package com.cpu.sistema_horario_java.app.util.exception;

public enum ExceptionType {
    RESOURCE_NOT_AVAILABLE("not.available"), ENTITY_NOT_FOUND("not.found"), DUPLICATE_ENTITY("duplicate"),
    WRONG_PASSWORD("wrong.password"), ENTITY_EXCEPTION("exception");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}